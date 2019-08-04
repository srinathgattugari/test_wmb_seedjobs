#!/usr/bin/env groovy
pipeline {
    agent { node { label 'master' } }
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '15'))
    }
    
    stages {
        stage('Building Jenkins Jobs from Master branch of seed-jobs-pf-non-prod repository') {
            steps {
                checkout scm
                jobDsl targets: ['jobs/**/*.groovy', 'views/**/*.groovy'].join('\n')
            }
        }
    }
}
