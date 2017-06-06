package grails3.quick.start

import grails3.quick.start.ExampleAggregateRoot.Status;

class DemoController {

	def index = {

		def command = [
			dateTo: new Date(),
			keyword: 'summary',
			max: 10,
			offset: 0
		]

		def result = ExampleAggregateRoot.search().list {

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

		log.info "${result.size()} results"

		render(view:'index', model: [message: 'Hello world', result: result])
	}
}
