#!/usr/bash
/usr/bin/java -Xmx1024m -jar ./LIUM_SpkDiarization-8.4.1.jar --fInputDesc=audio16kHz2sphinx:sphinx,1:1:0:0:0:0,31,0:0:0:0 --fInputMask=./RecordAudio.wav --sOutputMask=./RecordAudio.txt --sOutputFormat=txt --doCEClustering test
#dim = 31 semble etre bon
#dim = s*dim-1 + e*1*nbParametre
#esssayer de réduire la durée pour une meilleur detection et réduire délai traitement.