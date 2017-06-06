
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Hibernate Search Demo</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
        ${message}
        <ul>
        	<g:each in="${result}" var="item">
        		<li>${item}</li>
        	</g:each>
        </ul>
        </div>
    </body>
</html>
