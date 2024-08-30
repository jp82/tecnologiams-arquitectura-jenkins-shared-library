import pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.docker.DockerUtil

void call(script, String dockerImageName, String dockerImageTag, String registryDomain="integracion1-dr-local.artifactory.insi.sunat.peru", String registryPathRepository="microservices",String dockerImageFrom="integracion1-dr-local.artifactory.insi.sunat.peru/jdk/registry.access.redhat.com/ubi8/openjdk-17:1.18-2", String registryCredentialsId="artifactory-credentials"){
  println "docker: build and publish artifactory"
  def dockerfile = libraryResource 'pe/gob/sunat/tecnologiams/arquitectura/jenkins/shared/library/docker/Dockerfile'
  writeFile(file: "Dockerfile", text: dockerfile, encoding: "UTF-8")

  def docker = new DockerUtil(script)
  docker.buildAndPublish(dockerImageName, dockerImageTag, registryDomain, registryPathRepository, dockerImageFrom, registryCredentialsId)
}
