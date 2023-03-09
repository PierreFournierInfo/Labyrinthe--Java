#!/usr/bash
/usr/bin/java -Xmx1024m -jar ./LIUM_SpkDiarization-8.4.1.jar --fInputDesc=audio16kHz2sphinx:sphinx,1:1:0:3:1:1,19,0:0:0:0 --fInputMask=./RecordAudioBis.wav --sOutputMask=./RecordAudio.txt --sOutputFormat=txt --doCEClustering test
