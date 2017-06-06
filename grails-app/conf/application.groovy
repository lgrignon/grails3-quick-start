import grails.util.Environment
import java.io.InputStream

def trace(args) {
	println args
}

String serverName = InetAddress.getLocalHost().getHostName()
println '======================================================================================================='
println ' '
println 'RUNNING APPLICATION ON: ' + serverName + ' - ENVIRONMENT=' + Environment.getCurrent();
println ' '
println '======================================================================================================='

/****************************************************
 * Datasource configuration
 */
String databaseName = 'grails3_quickstart';
String databaseUser = databaseName + "_user";

trace("configuring datasource - databaseName=" + databaseName)

dataSources {
	
	dataSource {
		pooled          = true
		dbCreate        = "update"
		driverClassName = "com.mysql.jdbc.Driver"
		formatSql       = true
		logSql          = false
		username        = databaseUser
		password        = "password__1"
		url             = "jdbc:mysql://localhost:3306/" + databaseName + "?useUnicode=yes&characterEncoding=UTF-8"
		dialect         = "org.hibernate.dialect.MySQL5InnoDBDialect"
		properties {
			dbProperties {
				// Mysql specific driver properties
				// http://dev.mysql.com/doc/connector-j/en/connector-j-reference-configuration-properties.html
				// let Tomcat JDBC Pool handle reconnecting
				autoReconnect = true
			}
			minIdle = 5
			
			maxActive = 50
			maxIdle = 25
			initialSize = 5
			
			minEvictableIdleTimeMillis = 1800000
			timeBetweenEvictionRunsMillis = 1800000
			maxWait = 10000
	
			// testOnBorrow = true
			testWhileIdle = true
			// testOnReturn = true
			validationQuery = "SELECT 1"
			timeBetweenEvictionRunsMillis=60000
		}
	}
	
}

grails.plugins.hibernatesearch = {
	rebuildIndexOnStart false
	throwOnEmptyQuery false
//	fullTextFilter name: "reserveContext", impl: ReserveContextFullTextFilterFactory, cache: 'none'
}

grails.databinding.convertEmptyStringsToNull = false

grails.controllers.upload.maxFileSize = 1024 * 1024 * 512;
grails.controllers.upload.maxRequestSize = 1024 * 1024 * 512;

grails.resources.processing.enabled=false

grails {
	mail {
	  host = "localhost"
	  port = 25
	}
}

grails.mail.default.from='"Admin" <noreply@quickstart.fr>'


trace("app configured - synchronizing database with model");
