mvn install:install-file -Dfile=D:\IdeaProjects\push\xxl-job\xxl-job-core\target\xxl-job-core-2.2.1-SNAPSHOT.jar -DgroupId=com.xuxueli -DartifactId=xxl-job-core -Dversion=2.1.1-SNAPSHOT -Dpackaging=jar

轮训：
多台机器轮训运行，index都是0

分片广播：
多台机器同时指向，index不同