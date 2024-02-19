## Setting up Blinken / Color Table
https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf

## Overview of Methods
* setColor() - pass any valid double from -1.0 to 1.0
* setRed()
* setBlue()
* setGreen()
* setDefault() - WHITE - color that is displayed when the code is running on the roborio, but before any of the methods have been called to intentionally set a color
* setOff() - BLACK - actually turns leds off
* setAllianceColor()
* letsParty() - pattern that makes the leds dance

## Notes
When the robot is on but no code is running on the roborio, the blinken will default to the color that is stored in memory on its controller. That color is YELLOW

