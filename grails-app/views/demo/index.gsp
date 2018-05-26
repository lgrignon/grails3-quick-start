
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
        <h1>Search a field in:</h1>
        ${fieldsList}
        <br />
        <input id="searchField" type="text" placeholder="field..." value="" />
        <input id="searchTerm" type="text" placeholder="search..." value="" />
        <a href="#" onclick="window.search();">Submit</a>
        <h1>Here are the search results: </h1>
        <ul>
        	<g:each in="${result}" var="item">
        		<li>${item}</li>
        	</g:each>
        </ul>
        </div>
        <script type="text/javascript">
        function search() {
        	var url = document.location.href;
        	var field = document.getElementById('searchField').value.trim();
        	var term = document.getElementById('searchTerm').value.trim();
        	var searchUrl = url.substring(0, url.indexOf('?') == -1 ? url.length : url.indexOf('?'));
        	searchUrl = searchUrl.replace('#', '');
        	if (term != '' && field != '') {
	        	searchUrl += '?search=' + field + '_' + term
        	}
     		console.log('searchUrl=' + searchUrl);
        	document.location.href = searchUrl;
        }
        </script>
    </body>
</html>
