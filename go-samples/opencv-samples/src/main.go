package main

import (
	"fmt"
	"gocv.io/x/gocv"
	"image/color"
	"os"
)

func main() {
	if len(os.Args) < 2 {
		fmt.Println("Usage: ")
		return
	}

	draw(os.Args[1])

	fmt.Println("OpenCV cases")
}

func camera() {
	deviceId := 0
	webcam, err := gocv.OpenVideoCapture(deviceId)

}

func draw(filename string) {

}
