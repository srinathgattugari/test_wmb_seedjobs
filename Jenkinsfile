#!/usr/bin/env groovy
pipeline {

    agent {
        node {
            label 'NON-PRODUCTION-SLAVES'
        }
    }

    options {
        buildDiscarder(logRotator(daysToKeepStr: '14'))
    }

    environment {
        ENVIRONMENT = "NDT"

    }

    stages {
        stage('Checkout') {
            steps {
			checkout([$class: 'SubversionSCM', 
			additionalCredentials: [], 
			excludedCommitMessages: '', 
			excludedRegions: '', 
			excludedRevprop: '', 
			excludedUsers: '', 
			filterChangelog: false, 
			ignoreDirPropChanges: false, 
			includedRegions: '', 
			locations: [[cancelProcessOnExternalsFail: true, 
					 credentialsId: 'jenkins-svn-iccmb8', 
					 depthOption: 'infinity', 
					 ignoreExternalsOption: true, 
						 local: 'bin', 
						 remote: 'svn://subversion_dev/iccmb8/environments/JenkinsAutomation'], 
						[cancelProcessOnExternalsFail: true, 
						 credentialsId: 'jenkins-svn-iccmb8', 
						 depthOption: 'infinity', 
						 ignoreExternalsOption: true, 
						 local: 'bin/scripts/deploy/brokerfiles', 
						 remote: 'svn://subversion_dev/iccmb8/environments/Brokers/NDT'], 
						[cancelProcessOnExternalsFail: true, 
						 credentialsId: 'test_creds_svn', 
						 depthOption: 'infinity', 
						 ignoreExternalsOption: true, 
						 local: 'bar', 
						 remote: 'svn://subversion_dev/iccmb8/environments/BARFilesStaging/NDT']], 
						 quietOperation: true, 
						 workspaceUpdater: [$class: 'UpdateUpdater']])

					}
			
						   }
			
			
			stage('Build') {

			steps {
			withAnt(installation: 'LocalAnt') {
				sh "ant -buildfile ${WORKSPACE}/bin/scripts/svn-deploy.xml -Denv=${ENVIRONMENT} env.WORKSPACE=${workspace} env.JOB_NAME=${JOB_NAME} env.DRYRUN=${DRYRUN} env.PACKAGE=${PACKAGE} env.BARS=${SELECT_BAR_FILES} env.DISCOVERY=N"
			
						}
					}
				}
		    }
       
		
		}	
		
	