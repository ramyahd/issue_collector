@NonCPS
//def getBuildUser() {

//}
def call()
{
    //return currentBuild.rawBuild.getCause(Cause.UserIdCause).getUserId()
    def build = currentBuild.rawBuild
    def cause = build.getCause(hudson.model.Cause.UserIdCause.class)
    def name = cause.getUserName()
    sh "echo user: '${name}' > name.txt "
}

