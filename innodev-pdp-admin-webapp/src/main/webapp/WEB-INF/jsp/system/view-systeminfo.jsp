<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="row">
	<div class="col-md-6">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">本地文件系统配置</h3>
			</div>
			<div class="panel-body">
				<p>公用文件夹：${localFSSetting.store }</p>
				<p>临时文件夹：${localFSSetting.tmpStore }</p>
				<p>常量文件夹：${localFSSetting.constantStore }</p>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">WeedFS文件系统配置</h3>
			</div>
			<div class="panel-body">
				<p>Server信息：${weedFSConf.serverAddress }:${weedFSConf.serverPort }</p>
			</div>
		</div>
	</div>
</div>


<div class="row">
	<div class="col-md-6">
		<div class="panel panel-warning">
			<div class="panel-heading">
				<h3 class="panel-title">Elasticsearch配置</h3>
			</div>
			<div class="panel-body">
				<p>集群名称：${esConfig.clusterName }</p>
				<c:forEach var="model" items="${esConfig.addresses }">
					<p>地址信息：${model }</p>
				</c:forEach>
				<p>Elasticsearch Header插件地址：<a href="${esHeaderPluginName }" target="_blank">${esHeaderPluginName }</a></p>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-warning">
			<div class="panel-heading">
				<h3 class="panel-title">CentralServer配置</h3>
			</div>
			<div class="panel-body">
				<p>微信应用信息：
				<c:forEach var="model"  items="${centralServerConf.appnames }">
					${model } &nbsp;
				</c:forEach>
				</p>
				<p>CentralServer地址：<a href="${centralServerConf.centralServer }" target="_blank">${centralServerConf.centralServer }</a></p>
				<p>Zookeeper地址：${centralServerConf.zk }</p>
				<p>Zookeeper Connection Timeout(MS)地址：${centralServerConf.zkConnectionTimeoutMS }</p>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<c:forEach var="entry" items="${redisDataMap }">
		<div class="col-md-6">
			<div class="panel panel-danger">
				<div class="panel-heading">
					<h3 class="panel-title">Redis配置-${entry.key }</h3>
				</div>
				<div class="panel-body">
					<p>RedisID：${entry.value.id }</p>
					<p>地址信息：${entry.value.ip }:${entry.value.port}</p>
					<p>MaxActive：${entry.value.maxActive }</p>
					<p>MaxIdle：${entry.value.maxIdle }</p>
					<p>MaxWait：${entry.value.maxWait }</p>
					<p>Timeout：${entry.value.timeout }</p>
					<p>TestOnBorrow：${entry.value.testOnBorrow }</p>
					<p><a href="/system/redis-flushall.jhtml"  class="btn btn-default" data-todo="ajaxTodo"  title="确认清理全部Redis缓冲吗？">清理Redis缓冲</a></p>
				</div>
			</div>
		</div>
	</c:forEach>
</div>