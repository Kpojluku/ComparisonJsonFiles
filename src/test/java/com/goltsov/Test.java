package com.goltsov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.goltsov.model.JsonComparator;
import com.goltsov.model.JsonFile;

public class Test {
    public static void main(String[] args) {
        JsonComparator comparator = new JsonComparator();
        JsonFile jsonFile1;
        JsonFile jsonFile2;
        try {
            jsonFile1 = comparator.getJsonFile(getJson1());
            jsonFile2 = comparator.getJsonFile(getJson2());
            comparator.compare(jsonFile1, jsonFile2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public static String getJson1() {
        return "{\n" +
                "    \"metadata\": {\n" +
                "        \"description\": {\n" +
                "            \"version\": 2\n" +
                "        },\n" +
                "        \"application\": {\n" +
                "            \"name\": \"application_name\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"services\": [\n" +
                "        {\n" +
                "            \"service-short-name\": \"TEST_1\",\n" +
                "            \"service_name\": \"test-service-1\",\n" +
                "            \"artifact_type\": \"zip\",\n" +
                "            \"docker_registry\": \"docker.io:12345\",\n" +
                "            \"docker_image_name\": \"busybox\", \n" +
                "            \"docker_tag\": \"1.33.0\",\n" +
                "            \"force\": true,\n" +
                "            \"github_repository\": \"https://github.com/mirror/busybox\",\n" +
                "            \"github_branch\": \"master\",\n" +
                "            \"github_hash\": \"qwerty123456789qwerty123456789qwerty111q\",\n" +
                "            \"hashes\": {\n" +
                "                \"sha1\": \"749d65718qwec394eae890e7137898419b48b021\",\n" +
                "                \"sha256\": \"5123772326b5821234a130987930e1c46cvbnf10ce768199d56e4b874dfghjkl\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"artifacts\": [ \n" +
                "        {\n" +
                "            \"mvn\": [ \n" +
                "                { \n" +
                "                    \"groupId\": \"log4j\",\n" +
                "                    \"artifactId\": \"log4j\",\n" +
                "                    \"version\": \"1.2.17\",\n" +
                "                    \"service_name\": \"test-service-1\",\n" +
                "                    \"classifier\": \"sources\",\n" +
                "                    \"mvn_type\": \"pom\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"4123456f0f1erc9791fgfc5928c0d89a0bf31ae9\",\n" +
                "                        \"sha256\": \"zxc34f60ffb0265ecgiuy2f2cbd2a4763528fef2e1a752465ca3fd5a01bf3513\"\n" +
                "                    }\n" +
                "                }, {\n" +
                "                    \"groupId\": \"log4j\",\n" +
                "                    \"artifactId\": \"log4j\",\n" +
                "                    \"version\": \"1.2.17\",\n" +
                "                    \"service_name\": \"test-service-1\",\n" +
                "                    \"mvn_type\": \"jar\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"tei2471b518c3882a29dce746579875dd13f3fty\",\n" +
                "                        \"sha256\": \"hfyt975s4444f71f2ae76dfab1c51bf3cc9e55bi89d4e73944f54d8ed56ad28c\"\n" +
                "                    }\n" +
                "                }, {\n" +
                "                    \"groupId\": \"junit\",\n" +
                "                    \"artifactId\": \"junit\",\n" +
                "                    \"version\": \"4.12\",\n" +
                "                    \"classifier\": \"sources\", \n" +
                "                    \"mvn_type\": \"jar\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"a6c32b40bf3d76eca54e3c601e5d1470c86fcdfa\",\n" +
                "                        \"sha256\": \"9f43fea92033ad82bcad2ae44cec6c82abc9d6ee4b095cab921d11ead98bf2ff\"\n" +
                "                    }\n" +
                "                }, {\n" +
                "                    \"groupId\": \"org.hamcrest\",\n" +
                "                    \"artifactId\": \"hamcrest-core\",\n" +
                "                    \"version\": \"1.3\",\n" +
                "                    \"mvn_type\": \"jar\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"42a25dc3219429f0e5d060061f71acb49bf010a0\",\n" +
                "                        \"sha256\": \"66fdef91e9739348df7a096aa384a5685f4e875584cce89386a7a47251c4d8e9\"\n" +
                "                    }\n" +
                "                }, {\n" +
                "                    \"groupId\": \"org.hamcrest\",\n" +
                "                    \"artifactId\": \"hamcrest-core\",\n" +
                "                    \"version\": \"1.2\",\n" +
                "                    \"mvn_type\": \"pom\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"872e413497b906e7c9fa85ccc96046c5d1ef7ece\",\n" +
                "                        \"sha256\": \"fde386a7905173a1b103de6ab820727584b50d0e32282e2797787c20a64ffa93\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"target_repository\": \"mvn\"\n" +
                "        }, {\n" +
                "            \"service-short-name\": \"TEST_2\",\n" +
                "            \"service_name\": \"node-v0.6.11.tar.gz\",\n" +
                "            \"hashes\": {\n" +
                "                \"sha1\": \"5t8a614b22f1806f53ed68b1e68a2d3849130c15\",\n" +
                "                \"sha256\": \"e5nbyucce66bf62e142cf3430c137ac5f0c82b81f3f6099457a523afe3375916\"\n" +
                "            },\n" +
                "            \"file\": [\n" +
                "                \"https://nodejs.org/dist/node-v0.6.11.tar.gz\"\n" +
                "            ],\n" +
                "            \"target_repository\": \"file\"\n" +
                "        }, {\n" +
                "            \"service_name\": \"jenkins-2.223\",\n" +
                "            \"hashes\": {\n" +
                "                \"sha1\": \"fun7f57f7ff89adcff04c223a01cd06ab0932c21\",\n" +
                "                \"sha256\": \"3a38b40bf1b906146f6d330b8ad6211d9a50a9a97b16f92b5e6ce40b93a69238\"\n" +
                "            },\n" +
                "            \"file\": [\n" +
                "                \"http://ftp-nyc.osuosl.org/pub/jenkins/osx/jenkins-2.223.pkg\"\n" +
                "            ],\n" +
                "            \"target_repository\": \"file\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"script\": [\n" +
                "        {\n" +
                "            \"service-short-name\": \"mongocli\",\n" +
                "            \"start-point\": \"start-point.sh\",\n" +
                "            \"end-point\": \"end-point.sh\",\n" +
                "            \"script_name\": \"some_script_name\",\n" +
                "            \"hashes\": {\n" +
                "                \"sha1\": \"87jf7b9f6055bd924fd83e63ab1e0f16a57680dd\",\n" +
                "                \"sha256\": \"4di75ghj3d976b05b30ce3ce683b561d2bc726eb183e089dd80c8c9ae0ddb195\"\n" +
                "            },\n" +
                "            \"url\": \"https://fastdl.mongodb.org/mongocli/mongocli_1.12.0_windows_x86_64.zip\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"rpm\": {\n" +
                "        \"url\": \"http://mirror.centos.org/centos/7/os/x86_64/Packages/PyQt4-4.10.1-13.el7.i686.rpm\",\n" +
                "        \"rpm_repository_name\": \"some_rpm_repository\",\n" +
                "        \"hashes\": {\n" +
                "            \"sha1\": \"bft63mn2a79c45903d4edcf9877fa7201513c23e\",\n" +
                "            \"sha256\": \"25dy68e5044c719aa498d53260f84a0a0e344930b682af09ecb8481b63438917\"\n" +
                "        },\n" +
                "        \"service-short-name\": \"one\"\n" +
                "    },\n" +
                "    \"parameters\": {\n" +
                "        \"common\": {\n" +
                "            \"some-param\": \"test\",\n" +
                "            \"some-other-param\": \"test2\"\n" +
                "        },\n" +
                "        \"services\": {\n" +
                "            \"service_name\": {\n" +
                "                \"some-third-param\": \"test3\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
    }
    public static String getJson2(){
        return "{\n" +
                "    \"metadata\": {\n" +
                "        \"description\": {\n" +
                "            \"version\": 1\n" +
                "        },\n" +
                "        \"application\": {\n" +
                "            \"name\": \"application_name2\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"services\": [\n" +
                "        {\n" +
                "            \"service-short-name\": \"TEST_1\",\n" +
                "            \"service_name\": \"test-service-1\",\n" +
                "            \"artifact_type\": \"zip\",\n" +
                "            \"docker_registry\": \"docker.io:12345\",\n" +
                "            \"docker_image_name\": \"busybox\", \n" +
                "            \"docker_tag\": \"1.33.0\",\n" +
                "            \"force\": true,\n" +
                "            \"github_repository\": \"https://github.com/mirror/busybox\",\n" +
                "            \"github_branch\": \"master\",\n" +
                "            \"github_hash\": \"qwerty123456789qwerty123456789qwerty111q\",\n" +
                "            \"hashes\": {\n" +
                "                \"sha1\": \"749d65718qwec394eae890e7137898419b48b021\",\n" +
                "                \"sha256\": \"5123772326b5821234a130987930e1c46cvbnf10ce768199d56e4b874dfghjkl\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"artifacts\": [ \n" +
                "        {\n" +
                "            \"mvn\": [ \n" +
                "                { \n" +
                "                    \"groupId\": \"log4j\",\n" +
                "                    \"artifactId\": \"log4j\",\n" +
                "                    \"version\": \"1.2.17\",\n" +
                "                    \"service_name\": \"test-service-1\",\n" +
                "                    \"classifier\": \"sources\",\n" +
                "                    \"mvn_type\": \"pom\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"4123456f0f1erc9791fgfc5928c0d89a0bf31ae9\",\n" +
                "                        \"sha256\": \"zxc34f60ffb0265ecgiuy2f2cbd2a4763528fef2e1a752465ca3fd5a01bf3513\"\n" +
                "                    }\n" +
                "                }, {\n" +
                "                    \"groupId\": \"log4j\",\n" +
                "                    \"artifactId\": \"log4j\",\n" +
                "                    \"version\": \"1.2.17\",\n" +
                "                    \"service_name\": \"test-service-1\",\n" +
                "                    \"mvn_type\": \"jar\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"tei2471b518c3882a29dce746579875dd13f3fty\",\n" +
                "                        \"sha256\": \"hfyt975s4444f71f2ae76dfab1c51bf3cc9e55bi89d4e73944f54d8ed56ad28c\"\n" +
                "                    }\n" +
                "                }, {\n" +
                "                    \"groupId\": \"junit\",\n" +
                "                    \"artifactId\": \"junit\",\n" +
                "                    \"version\": \"4.12\",\n" +
                "                    \"classifier\": \"sources\", \n" +
                "                    \"mvn_type\": \"jar\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"a6c32b40bf3d76eca54e3c601e5d1470c86fcdfa\",\n" +
                "                        \"sha256\": \"9f43fea92033ad82bcad2ae44cec6c82abc9d6ee4b095cab921d11ead98bf2ff\"\n" +
                "                    }\n" +
                "                }, {\n" +
                "                    \"groupId\": \"org.hamcrest\",\n" +
                "                    \"artifactId\": \"hamcrest-core\",\n" +
                "                    \"version\": \"1.3\",\n" +
                "                    \"mvn_type\": \"jar\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"42a25dc3219429f0e5d060061f71acb49bf010a0\",\n" +
                "                        \"sha256\": \"66fdef91e9739348df7a096aa384a5685f4e875584cce89386a7a47251c4d8e9\"\n" +
                "                    }\n" +
                "                }, {\n" +
                "                    \"groupId\": \"org.hamcrest\",\n" +
                "                    \"artifactId\": \"hamcrest-core\",\n" +
                "                    \"version\": \"1.2\",\n" +
                "                    \"mvn_type\": \"pom\",\n" +
                "                    \"mvn_repository\": \"https://repo1.maven.org/maven2\",\n" +
                "                    \"hashes\": {\n" +
                "                        \"sha1\": \"872e413497b906e7c9fa85ccc96046c5d1ef7ece\",\n" +
                "                        \"sha256\": \"fde386a7905173a1b103de6ab820727584b50d0e32282e2797787c20a64ffa93\"\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"target_repository\": \"mvn\"\n" +
                "        }, {\n" +
                "            \"service-short-name\": \"TEST_2\",\n" +
                "            \"service_name\": \"node-v0.6.11.tar.gz\",\n" +
                "            \"hashes\": {\n" +
                "                \"sha1\": \"5t8a614b22f1806f53ed68b1e68a2d3849130c15\",\n" +
                "                \"sha256\": \"e5nbyucce66bf62e142cf3430c137ac5f0c82b81f3f6099457a523afe3375916\"\n" +
                "            },\n" +
                "            \"file\": [\n" +
                "                \"https://nodejs.org/dist/node-v0.6.11.tar.gz\"\n" +
                "            ],\n" +
                "            \"target_repository\": \"file\"\n" +
                "        }, {\n" +
                "            \"service_name\": \"jenkins-2.223\",\n" +
                "            \"hashes\": {\n" +
                "                \"sha1\": \"fun7f57f7ff89adcff04c223a01cd06ab0932c21\",\n" +
                "                \"sha256\": \"3a38b40bf1b906146f6d330b8ad6211d9a50a9a97b16f92b5e6ce40b93a69238\"\n" +
                "            },\n" +
                "            \"file\": [\n" +
                "                \"http://ftp-nyc.osuosl.org/pub/jenkins/osx/jenkins-2.223.pkg\"\n" +
                "            ],\n" +
                "            \"target_repository\": \"file\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"script\": [\n" +
                "        {\n" +
                "            \"service-short-name\": \"mongocli\",\n" +
                "            \"start-point\": \"start-point.sh\",\n" +
                "            \"end-point\": \"end-point.sh\",\n" +
                "            \"script_name\": \"some_script_name\",\n" +
                "            \"hashes\": {\n" +
                "                \"sha1\": \"87jf7b9f6055bd924fd83e63ab1e0f16a57680dd\",\n" +
                "                \"sha256\": \"4di75ghj3d976b05b30ce3ce683b561d2bc726eb183e089dd80c8c9ae0ddb195\"\n" +
                "            },\n" +
                "            \"url\": \"https://fastdl.mongodb.org/mongocli/mongocli_1.12.0_windows_x86_64.zip\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"rpm\": {\n" +
                "        \"url\": \"http://mirror.centos.org/centos/7/os/x86_64/Packages/PyQt4-4.10.1-13.el7.i686.rpm\",\n" +
                "        \"rpm_repository_name\": \"some_rpm_repository\",\n" +
                "        \"hashes\": {\n" +
                "            \"sha1\": \"bft63mn2a79c45903d4edcf9877fa7201513c23e\",\n" +
                "            \"sha256\": \"25dy68e5044c719aa498d53260f84a0a0e344930b682af09ecb8481b63438917\"\n" +
                "        },\n" +
                "        \"service-short-name\": \"one\"\n" +
                "    },\n" +
                "    \"parameters\": {\n" +
                "        \"common\": {\n" +
                "            \"some-param\": \"test\",\n" +
                "            \"some-other-param\": \"test2\"\n" +
                "        },\n" +
                "        \"services\": {\n" +
                "            \"service_name\": {\n" +
                "                \"some-third-param\": \"test3\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
    }
}
