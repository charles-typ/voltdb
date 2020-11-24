# Install dependencies
sudo apt-get -y install ant build-essential ant-optional default-jdk python cmake \
        valgrind ntp ccache git python-httplib2 python-setuptools python-dev apt-show-versions

# Make sure to switch java, javac, jar to JDK-8 instead of JDK-11
sudo update-alternatives --config java
sudo update-alternatives --config javac
sudo update-alternatives --config jar

# Build source code
ant

# Clean previous TPCC build
./run.sh clean &&

# Build TPCC source code
./run.sh jars &&

# Launch voltDB server
./run.sh server &&

# Wait for launch
/bin/sleep 10 &&

# Init tables for TPCC
./run.sh init &&

# Wait for init
/bin/sleep 5 &&

# Launch the TPCC client, result could be found in result.txt
start_vdb=$(date +%s%N) && echo "voltdb client started at $start_vdb" &&
./run.sh client | tee result.txt
end_vdb=$(date +%s%N)&& echo "voltdb client ended at $end_vdb" && echo "voltdb client run time $((end_vdb-start_vdb)) ns"
