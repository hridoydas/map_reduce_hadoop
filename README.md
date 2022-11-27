
# Map Reduce in hadoop for word count

Linux hadoop installation and using map reduce process for word counting from a text file.

## Authors

- [@hridoydas](https://github.com/hridoydas)


## Run Locally

Clone the project

```bash
  git clone https://github.com/hridoydas/map_reduce_hadoop.git
```

Go to the project directory

run hadoop in linux machine
```bash
  start-dfs.sh
  start-yarn.sh
```
to access access hadoop visit
```bash
  http://localhost:9870/
```



## Hadoop installation process in Linux

Create Hadoop Group and user

```bash
  sudo addgroup hadoop
  sudo adduser --ingroup hadoop hridoy
  groups hridoy
```
Install SSH
```bash
  sudo apt-get install ssh
```
Check SSH
```bash
  which ssh
  which sshd
```

Create and setup SSH
```bash
su hridoy
ssh-keygen
cat $HOME/.ssh/id_rsa.pub >> $HOME/.ssh/authorized_keys
ssh localhost
```

Install Hadoop (Download hadoop before installation)
```bash
  sudo -v
  su <main-user>
  sudo adduser hridoy sudo
  su hridoy
  sudo mkdir -p /usr/local/hadoop
  cd hadoop-3.3.4
  sudo mv * /usr/local/hadoop
  sudo chown -R hridoy:hadoop /usr/local/hadoop
```

Steup Configuration files
Step 1
```bash
  sudo nano ~/.bashrc
  #HADOOP VARIABLES START
  export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-i386
  export HADOOP_INSTALL=/usr/local/hadoop
  export PATH=$PATH:$HADOOP_INSTALL/bin
  export PATH=$PATH:$HADOOP_INSTALL/sbin
  export HADOOP_MAPRED_HOME=$HADOOP_INSTALL
  export HADOOP_COMMON_HOME=$HADOOP_INSTALL
  export HADOOP_HDFS_HOME=$HADOOP_INSTALL
  export YARN_HOME=$HADOOP_INSTALL
  export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_INSTALL/lib/native
  export HADOOP_OPTS="-Djava.library.path=$HADOOP_INSTALL/lib"
  #HADOOP VARIABLES END
```
Step 2
```bash
  sudo nano /usr/local/hadoop/etc/hadoop/hadoop-env.sh
  export JAVA_HOME=”JAVA PATH”
```
Step 3
```bash
  sudo mkdir -p /app/hadoop/tmp
```
Step 4
```bash
  sudo chown hridoy:hadoop /app/hadoop/tmp
```
Step 5
```bash
  sudo nano /usr/local/hadoop/etc/hadoop/core-site.xml
  <configuration>
  <property>
  <name>hadoop.tmp.dir</name>
  <value>/app/hadoop/tmp</value>
  <description>A base for other temporary
  directories.</description>
  </property>
  <property>
  <name>fs.default.name</name>
  <value>hdfs://localhost:54310</value>
  </property>
  </configuration>
```
Step 6
```bash
  sudo nano /usr/local/hadoop/etc/hadoop/mapred-site.xml
 <configuration>
 <property>
 <name>mapred.job.tracker</name>
 <value>localhost:54311</value>
 </property>
 </configuration>
```
Step 7
```bash
  sudo mkdir -p /usr/local/hadoop_store/hdfs/namenode
  sudo mkdir -p /usr/local/hadoop_store/hdfs/datanode
  sudo chown -R hridoy:hadoop /usr/local/hadoop_store
```

Step 8
```bash
  sudo nano /usr/local/hadoop/etc/hadoop/hdfs-site.xml
  <configuration>
  <property>
  <name>dfs.replication</name>
  <value>1</value>
  <description>Default block replication.
  The actual number of replications can be specified when the
  file is created.
  The default is used if replication is not specified in create
  time.
  </description>
  </property>
  <property>
  <name>dfs.block.size</name>
  <value>1048576</value>
  </property>
  <property>
  <name>dfs.namenode.name.dir</name>
  <value>file:/usr/local/hadoop_store/hdfs/namenode</value>
  </property>
  <property>
  <name>dfs.datanode.data.dir</name>
  <value>file:/usr/local/hadoop_store/hdfs/datanode</value>
  </property>
  </configuration>
```

Step 9
```bash
  hadoop namenode -format
  su mainuser
  cd /usr/local/hadoop/sbin
  ls
  sudo su hridoy
  start-dfs.sh
  start-yarn.sh
  jps
  stop-yarn.sh
  stop-dfs.sh
```

Step 10
```bash
  sudo nano core-site.xml
  <property>
  <name>fs.trash.interval</name>
  <value>3</value>
  </property>
  <property>
  <name>fs.trash.checkpoint.interval</name>
  <value>1</value>
  </property>
```
