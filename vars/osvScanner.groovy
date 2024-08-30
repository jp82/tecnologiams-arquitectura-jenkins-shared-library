import pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.osv.ScannerUtil

void call(script, String artifactoryBaseUrl="http://artifactory.insi.sunat.peru:7003/artifactory/", String registryDomain="integracion1-dr-local.artifactory.insi.sunat.peru", String artifactoryCredentialsId = "artifactory-credentials", String registryCredentialsId="artifactory-credentials"){
  println "ovs-scanner vulnerability"
  def ovs = new ScannerUtil(script)
  ovs.scanner(artifactoryBaseUrl, registryDomain, artifactoryCredentialsId, registryCredentialsId)
}

