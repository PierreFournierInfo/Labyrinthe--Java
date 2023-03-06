#!/usr/bash
if [[ "$OSTYPE" == "linux-gnu"* ]]; then /usr/bin/java -Xmx2024m -jar ./LIUM_SpkDiarization-4.2.jar --fInputMask=./RecordAudio.wav --sOutputMask=./RecordAudio.txt --doCEClustering test
elif [[ "$OSTYPE" == "win32" ]]; then  C/windows/system32/cmd -Xmx2024m -jar ./LIUM_SpkDiarization-4.2.jar --fInputMask=./RecordAudio.wav --sOutputMask=./RecordAudio.txt --doCEClustering test