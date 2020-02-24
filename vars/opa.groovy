@NonCPS
def getBuildUser() {
    return currentBuild.rawBuild.getCause(Cause.UserIdCause).getUserId()
}


def call()
{
getBuildUser()
}

