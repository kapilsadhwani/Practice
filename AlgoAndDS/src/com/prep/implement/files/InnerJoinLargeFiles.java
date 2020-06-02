package com.prep.implement.files;

import java.io.BufferedWriter;
import java.io.RandomAccessFile;

import com.sun.prism.impl.Disposer.Record;

public class InnerJoinLargeFiles {
	private void join(RandomAccessFile left, RandomAccessFile right, BufferedWriter output) throws Throwable {

	    long rightIndex = right.getFilePointer();

	    /*Record leftRecord = read(left);
	    Record rightRecord;

	    while((leftRecord )!=null){
	        right.seek(rightIndex);

	        while((rightRecord = read(right))!=null && leftRecord.getKey() > rightRecord.getKey()){
	            //skip right until it can match left
	            // store pointer because we don't need to return to earlier
	            rightIndex = right.getFilePointer();
	        }

	        while(rightRecord !=null && leftRecord.getKey()==rightRecord.getKey()){
	            write(output, leftRecord, rightRecord );
	            rightRecord = read(right));
	        }
	        leftRecord = read(left);
	        //you can test the need to skip this left record if you keep the previous key and the current rightRecord.getKey()
	    }*/
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
