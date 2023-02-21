#!/usr/bash
/usr/bin/java -Xmx2024m -jar ./LIUM_SpkDiarization-4.2.jar --fInputMask=./RecordAudio.wav --sOutputMask=./RecordAudio.txt --doCEClustering test
