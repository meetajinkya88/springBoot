#!/usr/bin/env bash

echo 'starting Customer Persitence in S3 client script'

read -p  "$(echo -e '***************************\n 1.Persist Records From File \n 2.Read Records \n 3.Update Records \n***************************\n\tEnter Your Choice:')" choice

echo " You have entered :" $choice


updateRecord()
{

        echo "updateRecord()...."
        read -p "$(echo -e 'Enter no of records to be updated :')" updateRecordCount
        read -p "$(echo -e 'Enter Batch Size:')" batchSize
        read -p "$(echo -e 'Enter PersistenceType (1 - mysql , 3- posgres, default- 1) :')" persistenceType
	read -p	"$(echo -e 'Enter No of Parallel Threads :')" threadCount

        echo 'Entered Details are:'
        echo 'updateRecordCount:' $updateRecordCount
        echo 'BatchSize:' $batchSize
	echo 'Persistence Type:' $persistenceType
	echo 'Parallel Thread Count:' $threadCount
	
	if [ -z "$batchSize" ] 
		then batchSize="50" 
	fi
	
	if [ -z "$updateRecordCount" ] 
		then updateRecordCount="100" 
	fi
        
	if [ -z "$persistenceType" ] 
		then persistenceType="1" 
	fi

	if [ -z "$threadCount" ] 
		then threadCount="1" 
	fi

	response=$(curl "http://localhost:20443/updateRecord?batchSize=$batchSize&updateRecordCount=$updateRecordCount&persistenceType=$persistenceType&threadCount=$threadCount") 
	echo 'Response Received is:'
	echo $response
}
persistRecord()
{

        echo "persistRecord()...."
        read -p "$(echo -e 'Enter File Absolute Path:')" filePath
        read -p "$(echo -e 'Enter Batch Size:')" batchSize
        read -p "$(echo -e 'Enter S3 Prefix :')" prefix
        read -p "$(echo -e 'Enter PersistenceType (1 - mysql , 2 - mysql+ s3, 3- posgres, 4 - posgres + s3 , 5 - mongo , default- 2) :')" persistenceType
	read -p "$(echo -e 'Enter Compression Type (1 - Deflate , 2 - LZ4, 3- Default) :')" compressionType

        echo 'Entered Details are:'
        echo 'File Path:' $filePath
        echo 'BatchSize:' $batchSize
        echo 'S3 Prefix:' $prefix
	echo 'Persistence Type:' $persistenceType
	echo 'Compression Type:' $compressionType

	if [ -z "$filePath" ] 
		then filePath="/home/ajinkyab/Desktop/pkey_300.txt" 
	fi
	
	if [ -z "$batchSize" ] 
		then batchSize="50" 
	fi
	
	if [ -z "$prefix" ] 
		then prefix="asdf" 
	fi
        
	if [ -z "$persistenceType" ] 
		then persistenceType="2" 
	fi

	if [ -z "$compressionType" ] 
		then compressionType="3" 
	fi

	response=$(curl "http://localhost:20443/persistRecord?batchSize=$batchSize&file=$filePath&prefix=$prefix&persistenceType=$persistenceType&compressionType=$compressionType") 
	echo 'Response Received is:'
	echo $response
}

readRecord()
{
	echo "readRecord()....."
	read -p	"$(echo -e 'Enter Thread Count:')" threadCount
	read -p "$(echo -e 'Enter Runing Time (in sec):')" runTime
        read -p "$(echo -e 'Enter PersistenceType (1 - mysql , 2 - mysql+ s3, 3- posgres, 4 - posgres + s3 , 5 - mongo , default- 2) :')" persistenceType
	read -p "$(echo -e 'Enter Compression Type (1 - Deflate , 2 - LZ4, 3- Default) :')" compressionType

	echo 'Entered Details are:'
	echo 'Thread Count:' $threadCount
	echo 'Runing Time:' $runTime
        echo 'Persistence Type:' $persistenceType
	echo 'Compression Type:' $compressionType


	if [ -z "$compressionType" ] 
		then compressionType="3" 
	fi

	response=$(curl "http://localhost:20443/readRecord?threadCount=$threadCount&runTime=$runTime&persistenceType=$persistenceType&compressionType=$compressionType")
	echo 'Response Received is:'
	echo $response
}

if [ "$choice" = "1" ]
then
	persistRecord
elif [ "$choice" = "2" ]
then
	readRecord
elif [ "$choice" = "3" ]
then
	updateRecord
else
	echo "Invalid Option....."
fi


