## to set up the cli-messages:
1 - you need to create an account at pusher.com (channel) and get your credentials (app_id, key, secret and cluster).
2 - then, you need to create the "resources" folder on src/main and create a file named 'application.yml'.
3 - now, you gonna past your credetials into the application.yml in the following format:


### application.yml (example)
env:
  app_id: "<your-app_id>"
  key: "<your-key>"
  secret: "<your-secret>"
  cluster: "<your-cluster>"
  channel_name: "<your-custom-channel_name>" # default: message-channel

server:
  port: 0 # make sure the server.port property is 0 (you can run multiple instances without any trouble). 


make sure that all dependencies in pom.xml are installed before run.
