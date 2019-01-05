package cn.demo.app.web.commons.filter;

import org.nutz.boot.AppContext;
import org.nutz.boot.starter.WebFilterFace;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@IocBean
public class RouteFilterStarter implements WebFilterFace {
	
	
	@Inject("refer:$ioc")
	protected Ioc ioc;
	
	@Inject
	protected PropertiesProxy conf;
	
	@Inject
	protected AppContext appContext;

	@Override
    public String getName() {
        return "routeFilterStarter";
    }

    @Override
    public String getPathSpec() {
        return "/*";
    }

    @Override
    public EnumSet<DispatcherType> getDispatches() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
    }

    @IocBean(name="routeFilter")
    public RouteFilter createRouteFilter() {
    	return new RouteFilter();
    }

    @Override
    public Filter getFilter() {
        return ioc.get(RouteFilter.class, "routeFilter");
    }

    @Override
    public Map<String, String> getInitParameters() {
        return new HashMap<>();
    }

    @Override
    public int getOrder() {
        return 11;
    }
}
