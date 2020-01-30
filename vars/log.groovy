def call(message)
{
 println(message)
 sh "echo ${message} >log.txt"
}
