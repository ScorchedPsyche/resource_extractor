
# Resource Extractor 
A Fabric mod that let's you extract Minecraft's resources into files like .json, .png, .txt, etc, for you to use wherever you want.

This is, at the moment, a very crude mod without any GUI. It does it's job in a simple and crude way without much ease of use.

**Status reference:**

![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) `Not yet implemented`  
![#1589F0](https://via.placeholder.com/15/1589F0/000000?text=+) `Implemented`  
![#c5f015](https://via.placeholder.com/15/c5f015/000000?text=+) `Production ready`

##  Features

![#1589F0](https://via.placeholder.com/15/1589F0/000000?text=+) Export item's icons to .PNG. Current dimensions: *16, 24, 32, 40, 48, 56, 64, 72,80, 88, 96, 104, 112, 120, 128, 136,144, 152, 160, 168, 176, 184, 192, 200,208, 216, 224, 232, 240, 248, 256*;  
![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) Export entity's icons to .PNG;  
![#1589F0](https://via.placeholder.com/15/1589F0/000000?text=+) Export item plain .txt list (easy copy-paste into Sheets or something);  
![#1589F0](https://via.placeholder.com/15/1589F0/000000?text=+) Export item JSON list with IDs, names (as in-game) and snake-lower case names (like_this_example).

![Example of exported resources](https://i.imgur.com/H1iwYJu.jpeg)

##  Usage
 
Use command: **/dae all**

Images and lists, for now, are being exported inside **/screenshots/**.

##  Why and how?
I needed item's icons and also a list of items that would tie the in-game descriptive name (ex.: Acacia Wood) with both the in-game ID (ex.: 1) and the snake-lower cased name (ex.: acacia_wood), with these same item's icons. With this I could easily tie a database to an image folder and just extract these again when a new version of Minecraft came out.

This is an automated way of having these resources extracted for me so that I can build other tools like websites. It works by injecting code into specific points of Minecraft's rendering engine and running this code when the command is triggered, so that the files are generated.

##  Send your suggestions
Let me know what else you'd like the mod to extract from Minecraft into a usable already converted file.