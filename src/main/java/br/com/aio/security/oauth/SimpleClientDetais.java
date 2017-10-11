package br.com.aio.security.oauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

public class SimpleClientDetais implements ClientDetails{

	private static final long serialVersionUID = 4449886366597121810L;

	private String clientId;

	private String clientSecret;

	private Set<String> scope = Collections.emptySet();

	private Set<String> resourceIds = Collections.emptySet();

	private Set<String> authorizedGrantTypes = Collections.emptySet();

	private Set<String> registeredRedirectUris;

	private Set<String> autoApproveScopes;

	private List<GrantedAuthority> authorities = Collections.emptyList();

	private Integer accessTokenValiditySeconds;

	private Integer refreshTokenValiditySeconds;

	private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setAutoApproveScopes(Collection<String> autoApproveScopes) {
		this.autoApproveScopes = new HashSet<String>(autoApproveScopes);
	}

	@Override
	public boolean isAutoApprove(String scope) {
		if (autoApproveScopes == null) {
			return false;
		}
		for (String auto : autoApproveScopes) {
			if (auto.equals("true") || scope.matches(auto)) {
				return true;
			}
		}
		return false;
	}

	public Set<String> getAutoApproveScopes() {
		return autoApproveScopes;
	}

	public boolean isSecretRequired() {
		return this.clientSecret != null;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public boolean isScoped() {
		return this.scope != null && !this.scope.isEmpty();
	}

	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Collection<String> scope) {
		this.scope = scope == null ? Collections.<String> emptySet()
				: new LinkedHashSet<String>(scope);
	}

	public Set<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(Collection<String> resourceIds) {
		this.resourceIds = resourceIds == null ? Collections
				.<String> emptySet() : new LinkedHashSet<String>(resourceIds);
	}

	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(Collection<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = new LinkedHashSet<String>(
				authorizedGrantTypes);
	}

	public Set<String> getRegisteredRedirectUri() {
		return registeredRedirectUris;
	}

	public void setRegisteredRedirectUri(Set<String> registeredRedirectUris) {
		this.registeredRedirectUris = registeredRedirectUris == null ? null
				: new LinkedHashSet<String>(registeredRedirectUris);
	}

	@SuppressWarnings("unused")
	private List<String> getAuthoritiesAsStrings() {
		return new ArrayList<String>(
				AuthorityUtils.authorityListToSet(authorities));
	}
	
	@SuppressWarnings("unused")
	private void setAuthoritiesAsStrings(Set<String> values) {
		setAuthorities(AuthorityUtils.createAuthorityList(values
				.toArray(new String[values.size()])));
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		this.authorities = new ArrayList<GrantedAuthority>(authorities);
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(
			Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public void setAdditionalInformation(Map<String, ?> additionalInformation) {
		this.additionalInformation = new LinkedHashMap<String, Object>(
				additionalInformation);
	}

	public Map<String, Object> getAdditionalInformation() {
		return Collections.unmodifiableMap(this.additionalInformation);
	}

	public void addAdditionalInformation(String key, Object value) {
		this.additionalInformation.put(key, value);
	}

}
