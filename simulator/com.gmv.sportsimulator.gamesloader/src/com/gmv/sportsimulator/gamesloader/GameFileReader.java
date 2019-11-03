//-----------------------------------------------------------------------------
//
// (C) 2017 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : Project
//
// File Name : RecipeTextReader.java
//
// Author : JeanSchuetz
//
// Creation Date : 10.05.2017
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.gamesloader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author JeanSchuetz
 *
 */
public class GameFileReader implements Iterable<String>
{
    public static final String COMMENT_SL = "//";

    public static final String COMMENT_MLS = "/*";

    public static final String COMMENT_MLE = "*/";

    private final File gameFile;

    private final ArrayList<String> textLines = new ArrayList<>();

    private int currentLine = 0;


    /**
     * Creates a new RecipeTextReader for the given file and reads its content
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public GameFileReader(File gameFile) throws FileNotFoundException, IOException
    {
        this.gameFile = gameFile;

        try (FileInputStream fis = new FileInputStream(this.gameFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                InputStreamReader isr = new InputStreamReader(bis);
                BufferedReader br = new BufferedReader(isr);)
        {
            for (String line = br.readLine(); line != null; line = br.readLine())
            {
                this.textLines.add(line);
            }
        }
    }
    
    public GameFileReader(ArrayList<String> textLines)
    {
        this.gameFile = null;
        this.textLines.addAll(textLines);
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<String> iterator()
    {
        return this.textLines.iterator();
    }

    /**
     * Resets the position of this reader to the first line.
     * 
     * @return this RecipeTextReader
     */
    public GameFileReader reset()
    {
        this.currentLine = 0;
        return this;
    }

    /**
     * @return the next line in the recipe, or null if there are no more lines
     */
    public String readLine()
    {
        if (hasMoreLines())
        {
            return this.textLines.get(this.currentLine++);
        }
        return null;
    }

    /**
     * Skips any empty line or comment lines until a token is encountered and
     * returns that line (or null if there are no more lines).
     * 
     * @return the next line in the recipe which is not empty, a single or
     *         multi-line comment, or null if there are no more lines
     */
    public String readTokenLine()
    {
        boolean insideMultiLineComment = false;
        for (String line = readLine(); line != null; line = readLine())
        {
            String trimmedLine = line.trim();
            if (trimmedLine.startsWith(COMMENT_SL))
            {
                continue;
            }

            if (!insideMultiLineComment && trimmedLine.startsWith(COMMENT_MLS))
            {
                insideMultiLineComment = true;
                continue;
            }

            if (insideMultiLineComment && trimmedLine.lastIndexOf(COMMENT_MLE) != -1)
            {
                insideMultiLineComment = false;
                continue;
            }

            if (insideMultiLineComment)
            {
                continue;
            }

            if (trimmedLine.isEmpty())
            {
                continue;
            }

            return line;
        }

        return null;
    }

    public boolean hasMoreLines()
    {
        return this.currentLine < this.textLines.size();
    }

    /**
     * Skips the specified amount of lines
     * 
     * @param numberOfLinesToSkip
     *            the (positive) amount of lines to skip
     * @return this RecipeTextReader
     */
    public GameFileReader skip(int numberOfLinesToSkip)
    {
        if (numberOfLinesToSkip < 0)
        {
            throw new IllegalArgumentException("amount to skip must be positive");
        }
        this.currentLine += numberOfLinesToSkip;
        return this;
    }

    /**
     * Skips all lines until the specified token occurs. The reader stops on the
     * line at which the token was found, i.e. the next invocation of readLine()
     * will return the line with the specified token.
     * 
     * @param token
     *            a String containing the token to which this reader shall skip
     *            to
     * @return this RecipeTextReader
     */
    public GameFileReader skipTo(String token)
    {
        for (String line = readLine(); line != null; line = readLine())
        {
            StringTokenizer st = new StringTokenizer(line);
            if (st.hasMoreTokens() && st.nextToken().equals(token))
            {
                backup();
                return this;
            }
        }
        return this;
    }

    /**
     * Same as skipTo(String token), with the difference that no skip occurs if
     * the specified token does not occur starting from the current position.
     * 
     * @param token
     *            a String containing the token to which this reader shall skip
     *            to
     * @return true if the token was found, false otherwise
     */
    public boolean skipToIfExists(String token)
    {
        final int currentPos = this.currentLine;
        skipTo(token);
        if (hasMoreLines())
        {
            return true;
        }
        else
        {
            this.currentLine = currentPos;
            return false;
        }
    }

    void backup()
    {
        this.currentLine--;
    }

}
