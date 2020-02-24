


@NonCPS
def getBuildUser()
{
    
    def build = currentBuild.rawBuild
    def cause = build.getCause(hudson.model.Cause.UserIdCause.class)
    def name = cause.getUserName()
    sh "echo user: '${name}' > name.txt "
}
def call()
{
    //return currentBuild.rawBuild.getCause(Cause.UserIdCause).getUserId()
        getbuildUser()
}

