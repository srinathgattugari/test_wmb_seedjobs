pipelineJob('tech-engg-wmb-pf-pre-prod-NDT-deploy') {
    parameters {
	    booleanParam('DRYRUN', false)
        choiceParam('ENVIRONMENT', ['NDT'], 'Please select the wmb environment.')
		activeChoiceParam('SELECT_BAR_FILES') {
            description('select the bar files')
            filterable()
            choiceType('CHECKBOX')
            groovyScript {
				script("def BARFILES = 'http://dxax2jbs001:1234/wmbliststagingenvbars?env=NDT'\ndef choices=[]\ndef body = new URL(BARFILES).getText()\nString barfiles = body.substring(body.indexOf('=')+1)\nbarfiles.trim()\nbarfiles.split(',').each{ choices.add(it) }\nreturn choices")
				fallbackScript('return ["error"]')
                         }

                  }

    }
    definition {
        cpsScm {
            scm {
                git {
                    branch('master')
                    remote {
                        credentials('jenkins-ci-op')
                        url('ssh://git@gitlab.deveng.systems:2222/Devops/wmb-pf.git')
                    }
                }
            }
            scriptPath('jenkinsfiles-pre-prod/Jenkinsfile-devops-tech_engg_wmb_pf_pre_prod_NDT_deploy')
        }
    }
    configure {
        it / definition / lightweight(true)
    }
}


