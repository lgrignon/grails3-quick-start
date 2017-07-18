package grails3.quick.start

class ExampleMongoEntity {
	
	String infos
	
	static mapWith = "mongo"
	
	static mapping = {
		collection "examples"
	}
}
