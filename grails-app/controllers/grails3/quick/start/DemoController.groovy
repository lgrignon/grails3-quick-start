package grails3.quick.start

import org.grails.orm.hibernate.HibernateMappingContextSessionFactoryBean
import org.hibernate.search.cfg.EntityDescriptor
import org.hibernate.search.cfg.PropertyDescriptor

import grails.core.GrailsApplication
import grails.plugins.hibernate.search.HibernateSearchGrailsPlugin
import grails3.quick.start.ExampleAggregateRoot.Status;

class DemoController {
	
	GrailsApplication grailsApplication;
	
	def index() {

		Map<String, PropertyDescriptor> indexedProperties = ExampleAggregateRoot.search().getIndexedProperties();
		println "indexedProperties: ${indexedProperties} ${ExampleAggregateRoot.search()?.getClass()}"
		
		List<ExampleAggregateRoot> result;
		if (params.search) {

			result = ExampleAggregateRoot.search().list {

				for (String filterDef : params.search.split("[:]")) {
					String field = filterDef.split('[_]')[0]
					String filterValue = filterDef.split('[_]')[1]
					String wildcardSearch = filterValue + "*"
					println "filter $field = $wildcardSearch"
					wildcard field, wildcardSearch
				}

				sort "publishedDate", "asc", Long
			}
		} else {

			def command = [
				dateTo: new Date(),
				keyword: 'summary',
				max: 10,
				offset: 0
			]

			result = ExampleAggregateRoot.search().list {

				wildcard "author", "te*"

				if ( command.dateTo ) {
					below "publishedDate", command.dateTo
				}

				if ( command.dateFrom ) {
					above "publishedDate", command.dateFrom
				}

				mustNot { keyword "status", Status.DISABLED }

				if ( command.keyword ) {
					should {
						command.keyword.tokenize().each { keyword ->

							def wild = keyword.toLowerCase() + '*'

							wildcard "author", wild
							wildcard "body", wild
							wildcard "summary", wild
							wildcard "title", wild
							wildcard "categories.name", wild
						}
					}
				}

				sort "publishedDate", "asc", Long

				maxResults command.max
				offset command.offset
			}
		}
		
		log.info "${result.size()} results"

		render(view:'index', model: [message: 'Hello world', result: result, fieldsList: indexedProperties.keySet()])
	}
}
