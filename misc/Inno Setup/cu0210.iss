[Setup]
AppName=Color Utility
AppVersion=0.b2
DefaultDirName={pf}\Color Utility
DefaultGroupName=Color Utility
UninstallDisplayIcon={app}\Color Utility.exe
Compression=lzma2
SolidCompression=yes
OutputDir=desktop

[Files]
Source: "Color Utility.exe"; DestDir: "{app}"

[Icons]
Name: "{group}\Color Utility"; Filename: "{app}\Color Utility.exe"
