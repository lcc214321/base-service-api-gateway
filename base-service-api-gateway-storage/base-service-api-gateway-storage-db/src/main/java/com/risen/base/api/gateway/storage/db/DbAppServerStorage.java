package com.risen.base.api.gateway.storage.db;

import com.risen.base.api.gateway.cache.AppServer;
import com.risen.base.api.gateway.cache.AppServerStorage;
import com.risen.base.api.gateway.storage.db.mapper.GwAppInfoMapper;
import com.risen.base.api.gateway.storage.db.mapper.GwAppServerMapper;
import com.risen.base.api.gateway.storage.db.model.GwAppInfo;
import com.risen.base.api.gateway.storage.db.model.GwAppServer;
import com.risen.base.api.gateway.util.BeanCopierUtils;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

/**
 * @author mengxr
 * @since 1.0
 */
public class DbAppServerStorage implements AppServerStorage {

    private GwAppInfoMapper gwAppInfoMapper;

    private GwAppServerMapper gwAppServerMapper;

    public DbAppServerStorage(GwAppInfoMapper gwAppInfoMapper, GwAppServerMapper gwAppServerMapper) {
        this.gwAppInfoMapper = gwAppInfoMapper;
        this.gwAppServerMapper = gwAppServerMapper;
    }

    /**
     * 生成AppServer
     *
     * @param appId
     * @return
     */
    @Override
    public AppServer generateAppServer(String appId) {
        GwAppInfo gwAppInfo = gwAppInfoMapper.selectByAppkey(appId);
        if (Objects.nonNull(gwAppInfo)) {
            AppServer appServer = BeanCopierUtils.copierTargetBean(gwAppInfo, GwAppInfo.class, AppServer.class);
            Set<GwAppServer> gwAppServers = gwAppServerMapper.selectListByAppkey(appId);
            if (!CollectionUtils.isEmpty(gwAppServers)) {
                Set<AppServer.Server> servers = BeanCopierUtils.copierTargetBeanSet(gwAppServers, GwAppServer.class, AppServer.Server.class);
                appServer.setServers(servers);
            }
            return appServer;
        }
        return null;
    }
}
