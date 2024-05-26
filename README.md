# Getting Started

DispatcherServletAutoConfiguration 自动创建了 DispatcherServlet Bean。

DispatcherServlet 继承了 ApplicationContextAware 和 EnvironmentAware。
为了注册 ConfigurableWebEnvironment 和 WebApplicationContext 属性到 DispatcherServlet。

当第一个请求到达 DispatcherServlet 会触发 Servlet 的初始化，init 方法执行，有且仅执行一次。

init -> onRefresh -> initStrategies

```java
protected void initStrategies(ApplicationContext context) {
    initMultipartResolver(context);
    initLocaleResolver(context);
    initThemeResolver(context);
    initHandlerMappings(context);
    initHandlerAdapters(context);
    initHandlerExceptionResolvers(context);
    initRequestToViewNameTranslator(context);
    initViewResolvers(context);
    initFlashMapManager(context);
}
```