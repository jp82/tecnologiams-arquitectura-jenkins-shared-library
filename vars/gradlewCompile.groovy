import pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.gradle.GradlewUtil

//example arg = "--full-stacktrace --refresh-dependencies --info"
//"-Dorg.gradle.caching=true --full-stacktrace --info"

void call(script, String artifactoryBaseUrl="http://artifactory.insi.sunat.peru:7003/artifactory/", String extraArgs="-Dorg.gradle.caching=true --full-stacktrace", String registryDomain="integracion1-dr-local.artifactory.insi.sunat.peru", String artifactoryCredentialsId = "artifactory-credentials", String registryCredentialsId="artifactory-credentials"){
  println "gradlew: Compile() -> clean bootJar"
  def gradle = new GradlewUtil(script)
  gradle.cleanAndBootJar(extraArgs, artifactoryBaseUrl, registryDomain, artifactoryCredentialsId, registryCredentialsId)
}
