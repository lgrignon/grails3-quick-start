package grails3.quick.start

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils

class BootStrap {

    def init = { servletContext ->
		
		println "BOOTSTRAP START"
		
		ExampleAggregateRoot.withTransaction {
		
			def category = new ExampleCategory();
			category.name = 'test';
			category.save(flush : true);
			
			def model = new ExampleAggregateRoot();
			model.categories.add(category);
			model.author = 'test' + RandomStringUtils.randomAlphabetic(5);
			model.body = "test body" + RandomStringUtils.randomAlphabetic(5);
			model.publishedDate = new Date();
			model.summary = 'summary' + RandomStringUtils.randomAlphabetic(5);
			model.title = 'test title' + RandomStringUtils.randomAlphabetic(5);
			model.status = ExampleAggregateRoot.Status.ENABLED;
			model.price = 2.1;
			model.someInteger = 1;
			model.save(flush: true);
			
			println 'model created'
			
			ExampleAggregateRoot.search().createIndexAndWait()
	
		}
				
		println "BOOTSTRAP ENDED"
    }
	
	
    def destroy = {
    }
}
