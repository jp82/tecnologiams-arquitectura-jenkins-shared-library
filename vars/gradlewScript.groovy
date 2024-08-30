import pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.gradle.GradlewUtil
//example arg = "clean pushImage --refresh-dependencies --full-stacktrace --info"
//example arg = "clean pushImage --refresh-dependencies --stacktrace"

void call(script, String args, String artifactoryBaseUrl="http://artifactory.insi.sunat.peru:7003/artifactory/", String registryDomain="integracion1-dr-local.artifactory.insi.sunat.peru", String artifactoryCredentialsId = "artifactory-credentials", String registryCredentialsId="artifactory-credentials"){
  println "gradlewScript: ./gradlew ${args}"
  def gradle = new GradlewUtil(script)
  gradle.runScript(args, artifactoryBaseUrl, registryDomain, artifactoryCredentialsId, registryCredentialsId)
}
