package grails3.quick.start

class ExampleAggregateRoot {
	
	String author
	String body
	Date publishedDate
	String summary
	String title
	Status status
	Double price
	Integer someInteger

	List<ExampleCategory> categories = [];
	
	static enum Status {
		DISABLED, PENDING, ENABLED
	}

	static hasMany = [categories: ExampleCategory]

	static search = {
		// fields
		author index: 'yes', analyzer: 'ngram', boost: 5.9
		body termVector: 'with_positions'
		publishedDate date: 'day', sortable: true
		summary boost: 5.9
		title index: 'yes'
		status index: 'yes'
		categories indexEmbedded: [includeEmbeddedObjectId: true, depth: 1]
		price numeric: 2, analyze: false
//		someInteger index: 'yes', bridge: ['class': PaddedIntegerBridge, params: ['padding': 10]]
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + properties;
	}
}
