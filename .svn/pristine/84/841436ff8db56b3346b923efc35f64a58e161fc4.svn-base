package com.thinkgem.jeesite.modules.sys.security;

import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.web.Servlets;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class MyCasRealm extends CasRealm {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private SystemService systemService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// return super.doGetAuthenticationInfo(token);
		CasToken casToken = (CasToken) token;
		if (token == null) {
			return null;
		}
		// 获取ticket
		String ticket = (String) casToken.getCredentials();
		if (!org.apache.shiro.util.StringUtils.hasText(ticket)) {
			return null;
		}
		TicketValidator ticketValidator = ensureTicketValidator();
		try {
			// 回传ticket到服务端验证，验证通过就进入下一行，可以获取登录后的相关信息，否则直接抛异常，即验证不通过
			Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
			AttributePrincipal casPrincipal = casAssertion.getPrincipal();
			String userId = casPrincipal.getName();
			User user = getSystemService().getUserByLoginName(userId);
			if (user != null) {
				Principal p = new Principal(user, false);
				PrincipalCollection principalCollection = new SimplePrincipalCollection(p, getName());
				return new SimpleAuthenticationInfo(principalCollection, ticket);
			} else {
				return null;
			}
		} catch (TicketValidationException e) {
			logger.error("票据认证失败", e);
			throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))) {
			Collection<Session> sessions = getSystemService().getSessionDao().getActiveSessions(true, principal,
					UserUtils.getSession());
			if (sessions.size() > 0) {
				// 如果是登录进来的，则踢出已在线用户
				if (UserUtils.getSubject().isAuthenticated()) {
					for (Session session : sessions) {
						getSystemService().getSessionDao().delete(session);
					}
				}
				// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
				else {
					UserUtils.getSubject().logout();
					throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
				}
			}
		}
		User user = getSystemService().getUserByLoginName(principal.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> list = UserUtils.getMenuList();
			for (Menu menu : list) {
				if (StringUtils.isNotBlank(menu.getPermission())) {
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(menu.getPermission(), ",")) {
						info.addStringPermission(permission);
					}
				}
			}
			// 添加用户权限
			info.addStringPermission("user");
			// 添加用户角色信息
			for (Role role : user.getRoleList()) {
				info.addRole(role.getEnname());
			}
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(user);
			// 记录登录日志
			LogUtils.saveLog(Servlets.getRequest(), "系统登录");
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * Title:获取系统业务对象 Description:获取系统业务对象
	 * 
	 * @param
	 * @return
	 * @Author:holyspirit
	 * @Create Date: 2017-7-6
	 * @Modifier:
	 * @Modify Date:
	 */
	public SystemService getSystemService() {
		if (systemService == null) {
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}
}