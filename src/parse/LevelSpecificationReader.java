package parse;

import biuoop.DrawSurface;
import levels.LevelInfoImpl;
import levels.LevelInformation;
import settings.Velocity;
import sprites.Block;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is a LevelSpecificationReader object.
 * Its job is to get a file name and returns a list of LevelInformation objects.
 */
public class LevelSpecificationReader {
    private int size;

    public List<LevelInformation> fromReader(BufferedReader reader, DrawSurface d, String absolutePath) {
        List<LevelInformation> listRes = new ArrayList<>();
        LevelInfoImpl thisLevelInformation = null;
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;
        String thisLine = null;
        boolean inBlocksFlag = false;
        BufferedReader tempBufferReader = null;
        try {
            while ((thisLine = reader.readLine()) != null) { //while there are lines
                if (thisLine.isBlank()) { // checks if line not empty
                    System.out.println("i got an empty line");
                } else if (thisLine.charAt(0) == '#') { // checks first char
                    System.out.println("i got a comment");
                } else if (thisLine.equals("START_LEVEL")) {
                    thisLevelInformation = new LevelInfoImpl();
                } else if (thisLine.equals("END_LEVEL")) {
                    listRes.add(thisLevelInformation);
                } else if (thisLine.equals("START_BLOCKS")) {
                    blocksFromSymbolsFactory = new BlocksFromSymbolsFactory();
                    inBlocksFlag = true;
                    assert thisLevelInformation != null;

                    File tempFile = new File(absolutePath + "/" + thisLevelInformation.getBlocksDefs());

                    tempBufferReader = new BufferedReader(new FileReader(tempFile));
                    blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(tempBufferReader, d, absolutePath);

                } else if (thisLine.equals("END_BLOCKS")) {
                    inBlocksFlag = false;
                    assert thisLevelInformation != null;
                    int sumX = thisLevelInformation.getBlocksStartX();
                    int sumY = thisLevelInformation.getBlocksStartY();
                    for (String lineRow : thisLevelInformation.getBlocksRowFormat()) { //all block line info into list str
                        int maxY = 0; //reset in each row
                        for (int i = 0; i < lineRow.length(); i++) {
                            assert blocksFromSymbolsFactory != null;
                            // create block
                            System.out.println("sumx: " + sumX + " sum y: " + sumY);
                            String currentChar = String.valueOf(lineRow.charAt(i));
                            Block b = null;
                            if (blocksFromSymbolsFactory.isBlockSymbol(currentChar)) {
                                b = blocksFromSymbolsFactory.getBlock(currentChar, sumX, sumY);
                                thisLevelInformation.getBlocks().add(b); //add block to the block list

                                System.out.println("created block " + currentChar);
                                //update counters
                                sumX += (int) b.getRect().getWidth();
                                if ((int) b.getRect().getHeight() > maxY) {
                                    maxY = (int) b.getRect().getHeight();
                                }
                            } else if (blocksFromSymbolsFactory.isSpaceSymbol(currentChar)) {
                                int spacer = blocksFromSymbolsFactory.getSpaceWidth(currentChar);
                                System.out.println("created spacer " + currentChar);
                                sumX += spacer;
                                if (maxY < thisLevelInformation.getRowHeight()) {
                                    maxY = thisLevelInformation.getRowHeight();
                                }
                            } else {
                                throw new ParseException("couldn't find currentChar " + currentChar);
                            }
                        }

                        sumY += maxY;// update height counter
                    }
                } else if (inBlocksFlag) {
                    thisLevelInformation.addRowToBlocksString(thisLine);
                } else {
                    String[] myKeyValue = thisLine.split(":");
                    switch (myKeyValue[0]) {
                        case "level_name":
                            assert thisLevelInformation != null;
                            thisLevelInformation.setLevelName(myKeyValue[1]);
                            break;
                        case "ball_velocities": {
                            List<Velocity> velList = new ArrayList<>();
                            String[] velocityArr = myKeyValue[1].split(" ");
                            for (String s : velocityArr) {
                                String[] stringXY = s.split(",");
                                Velocity v = new Velocity(Double.parseDouble(stringXY[0]), Double.parseDouble(stringXY[1]));
                                velList.add(v);
                            }
                            assert thisLevelInformation != null;
                            thisLevelInformation.setInitialBallVelocities(velList);
                            break;
                        }
                        case "background": {
                            assert thisLevelInformation != null;
                            thisLevelInformation.setBackground(null); //TODO add backgraound sprite
                            break;
                        }
                        case "paddle_speed":
                            assert thisLevelInformation != null;
                            thisLevelInformation.setPaddleSpeed(Integer.parseInt(myKeyValue[1]));
                            break;
                        case "paddle_width":
                            assert thisLevelInformation != null;
                            thisLevelInformation.setPaddleWidth(Integer.parseInt(myKeyValue[1]));
                            break;
                        case "block_definitions":
                            assert thisLevelInformation != null;
                            thisLevelInformation.setBlocksDefs(myKeyValue[1]);
                            break;
                        case "blocks_start_x":
                            assert thisLevelInformation != null;
                            thisLevelInformation.setBlocksStartX(Integer.parseInt(myKeyValue[1]));
                            break;
                        case "blocks_start_y":
                            assert thisLevelInformation != null;
                            thisLevelInformation.setBlocksStartY(Integer.parseInt(myKeyValue[1]));
                            break;
                        case "row_height":
                            assert thisLevelInformation != null;
                            thisLevelInformation.setRowHeight(Integer.parseInt(myKeyValue[1]));
                            break;
                        case "num_blocks":
                            assert thisLevelInformation != null;
                            thisLevelInformation.setNumBlocks(Integer.parseInt(myKeyValue[1]));
                            break;
                        default:
                            throw new Exception("no case found " + myKeyValue[0]);
                    }
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                Exception e) {
            e.printStackTrace();

        }
        return listRes;
    }

    /**
     * this methods reads a table from file and return it.
     *
     * @return a new score table with the updated values according to the data in the file.
     * if the file does not exist, or there is a proble reading it --- an empty table is returned.
     */
//    public void loadFromFile() {
//        try {
//            if (filename.exists()) {
//                Path filePath = Paths.get(filename.getAbsolutePath());
//                Charset charset = StandardCharsets.ISO_8859_1;
//                List<String> lines = Files.readAllLines(filePath, charset); //list of lines in file
//                String line0 = lines.get(0); //our first line
//                String[] part0 = line0.split(" "); //spilt at " "
//                String stringSize = part0[part0.length - 1]; //last split
//                this.size = Integer.parseInt(stringSize);
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("Unable to find file: " + filename.getName());
//        } catch (IOException e) {
//            System.err.println("IOException" + e.getMessage());
//        } finally {
//            System.out.println("loadFromFile size is - " + this.size);
//        }
//    }
}