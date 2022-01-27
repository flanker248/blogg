cd C:\chirag\plgrnd\blogging\blogg
call mvn clean install
cd C:\chirag\plgrnd\
copy C:\chirag\plgrnd\blogging\blogg\target\*.jar . 
#ren *.jar blogg.jar
scp -i cbyk-t2-instance-1.pem *.jar ubuntu@18.140.155.162:
ssh -i cbyk-t2-instance-1.pem ubuntu@18.140.155.162