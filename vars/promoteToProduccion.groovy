/**
* @author      Wilmer Orlando Diaz Guevara (Arquitecto Soluciones)
* @version     1.0
*/

import pe.gob.sunat.tecnologiams.arquitectura.jenkins.shared.library.artifactory.ArtifactoryUtil
void call(script, String artifactoryBaseUrl="http://artifactory.insi.sunat.peru:7003/artifactory/", String dockerRepository, String dockerImageVersion, String artifactoryUserName = "-", String artifactoryPwd = "",  String artifactoryCredentialsId = "artifactory-credentials"){
 
    println "promoteToProduccion: Promocionar imagen docker a produccion"

    def originRepoKey = "calidad1-dr-local"
    def targetRepoKey = "produccion1-dr-local"

    def bodyJSON = libraryResource 'pe/gob/sunat/tecnologiams/arquitectura/jenkins/shared/library/artifactory/bodyPromote.json'
    bodyJSON = bodyJSON.replaceAll(/\$\(TARGET_REPO_KEY\)/,targetRepoKey);
    bodyJSON = bodyJSON.replaceAll(/\$\(DOCKER_REPOSITORY\)/,dockerRepository);
    bodyJSON = bodyJSON.replaceAll(/\$\(TAG\)/,dockerImageVersion);
    
    def artifactoryUtil = new ArtifactoryUtil(script)
    artifactoryUtil.dockerPromote(artifactoryBaseUrl, originRepoKey, bodyJSON, artifactoryUserName, artifactoryPwd, artifactoryCredentialsId)
}

