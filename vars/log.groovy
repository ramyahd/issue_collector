def call(message)
{
 println(message)
 def message1= $message
 echo "${message1}"
 sh "echo ${message} >log.txt"
}
