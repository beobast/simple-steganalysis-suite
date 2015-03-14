# Introduction #

The program handles only one image at a time.
First open an image by clicking "File -> Open Image"


# Features #


## LSB Enhancement ##

The most famous visual attack, described in :

_[A. Westfeld, A. Pfitzmann, Attacks on steganographic systems, in: Information Hiding, LNCS, vol. 1768, Springer-Verlag, Heidelberg, 1999](http://simple-steganalysis-suite.googlecode.com/svn/wiki/articles/Attacks_on_Steganographic_Systems.pdf)_

Just extract the LSB of each pixel (or LSB of red, green and blue value for RGB images).

  * If LSB = 1 then set pixel (or color value) to 255
  * If LSB = 0 then set pixel (or color value) to 0

![http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/lena512.jpg](http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/lena512.jpg)

Stego image

![http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/lena_lsb.png](http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/lena_lsb.png)

LSB Enhancement result (embedding rate : 50%)



## Chi-Square Attack ##

The most famous statistical attack, described in :

_[A. Westfeld, A. Pfitzmann, Attacks on steganographic systems, in: Information Hiding, LNCS, vol. 1768, Springer-Verlag, Heidelberg, 1999](http://simple-steganalysis-suite.googlecode.com/svn/wiki/articles/Attacks_on_Steganographic_Systems.pdf)_

![http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/lsbEnhancement.png](http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/lsbEnhancement.png)

Stego image (left) and LSB Enhancement result (right)

![http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/chiSquare.png](http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/chiSquare.png)

Chi-Square attack from bottom to top (Chi-Square p-value in red, average LSB in blue)



## Neighbourhood Histogram ##

Let us consider a normal RGB image. Each pixel has 3 components : red, green and blue. There are `(2^8 * 2^8 * 2^8 = 2^24) = 16.7M`
possible colors. A true image does not contain all possible colors, this would require at least 16.7M pixels.

The difference between 2 adjacent pixels will most likely be more than +/- 1, even if many pixels have the same color.

Let denote pixel p1(r,g,b) and pixel p2(r+/-1, g+/-1, b+/-1). These pixels are called "neighbours". A normal image contains 4-5 neighbours colours for each pixel on average. LSB embedding produces up to 7 neighbours colours for every colours in the image. Some steganographic techniques increment or decrement LSB, producing up to 26 neighbours for each modified pixel :

|(r − 1, g − 1, b − 1)|(r, g − 1, b − 1)|(r + 1,   g − 1,   b − 1)|
|:--------------------------|:--------------------|:----------------------------|
|(r − 1, g − 1, b)    |(r, g − 1, b)    |(r + 1,   g − 1,   b)    |
|(r − 1, g − 1, b + 1)|(r, g − 1, b + 1)|(r + 1,   g − 1,   b + 1)|
|(r − 1, g,     b − 1)|(r, g,     b − 1)|(r + 1,   g,       b − 1)|
|(r − 1, g,     b)    | **(r, g, b)**     |(r + 1,   g,       b)    |
|(r − 1, g,     b + 1)|(r, g,     b + 1)|(r + 1,   g,       b + 1)|
|(r − 1, g + 1, b − 1)|(r, g + 1, b − 1)|(r + 1,   g + 1,   b − 1)|
|(r − 1, g + 1, b)    |(r, g + 1, b)    |(r + 1,   g + 1,   b)    |
|(r − 1, g + 1, b + 1)|(r, g + 1, b + 1)|(r + 1,   g + 1,   b + 1)|

![http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/neighbourhood.png](http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/neighbourhood.png)

Neighbourhood histogram of a normal image (top) and steganogram with 40 KB embedded (bottom)

Source : _[A.Westfeld, Detecting low embedding rates, in: F.A.P. Petitcolas (Ed.), Information Hiding. 5th InternationalWorkshop, IH 2002 Noordwijkerhout, The Netherlands, October 7–9, 2002, Springer-Verlag, Berlin, 2003](http://simple-steganalysis-suite.googlecode.com/svn/wiki/articles/Detecting_Low_Embedding_Rates.pdf)_


## Pixel Difference Histogram and attack ##

Denote the intensity value of the image I at the position (i,j) as I(i,j), and the difference image is defined as D(i,j)=I(i,j)-I(i,j+1). The difference image histogram is defined as the histogram of the difference image D. It is generally believed that the difference image follows a generalized Gaussian distribution.

![http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/lena_histogram.jpg](http://simple-steganalysis-suite.googlecode.com/svn/wiki/images/lena_histogram.jpg)

A  new  steganalytic  technique  based  on  the  difference image histogram aimed at LSB steganography is proposed. Translation    coefficients between difference image histograms are defined as a measure of the weak correlation between the least significant bit(LSB) plane and the remained bit planes, and then used to construct a
classifier to discriminate the stego-image from the carrier-
image. The algorithm can not only detect the existence of hidden  messages embedded using sequential or random LSB replacement in images reliably, but also estimate the amount of hidden messages exactly.  Experimental results show that for raw lossless compressed images the new algorithm had a better performance than the RS  analysis method and improves the computation speed significantly.

Source : _[Tao Z and Xijian P, (2003) “Reliable detection of lsb steganography based on the difference image histogram”, Proc. IEEE ICAAP, Part III, 2003](http://simple-steganalysis-suite.googlecode.com/svn/wiki/articles/Reliable_Detection_of_LSB_Steganography_Based_on_the_Difference_Image_Histogram.pdf)_

## Primary Sets ##

  * This technique is based on statistics of sets defined on neighboring pixel pairs.
  * Some of these sets have equal expected cardinalities, if the pixels pairs are drawn from a continuous tone-image.
  * Random LSB flipping causes transitions between the sets with given probabilities, and alter the statistical relations between their cardinalities.
  * Analysis leads to a quadratic equation to estimate the embedding message length with high precision.

Consider the partition of an image into pairs of horizontally adjacent pixels.
Let P be the set of all these pixel pairs. Define the subsets X, Y and Z of P as
follows:
  * X is the set of pairs (u, v) ∈ P such that v is even and u < v, or v is odd and u > v.
  * Y is the set of pairs (u, v) ∈ P such that v is even and u > v, or v is odd and u < v.
  * Z is the subset of pairs (u, v) ∈ P such that u = v.


After having made the above definitions, the authors make the assumption that statistically we will have |X| = |Y |.
This assumption is true for natural images as the gradient of intensity function in any direction is equally likely to be positive or negative.


Furthermore, they partition the set Y into two subsets W and V , with W
being the set of pairs in P of the form (2k, 2k+1) or (2k+1, 2k), and V = Y − W.
Then P = X ∪ W ∪ V ∪ Z. They call sets X, V , W and Z as primary sets.


When LSB embedding is done, pixel values get modified and so does the
membership of pixel pairs in the primary sets. More specifically, given a pixel pair (u, v), they identify the following four situations:
  * both values u and v remain unmodified;
  * only v is modified;
  * only u is modified;
  * both u and v are modified.

By some simple algebraic manipulations, the authors finally arrive at the equation 0.5γp^2 + (2|X| − |P|)p + |Y| − |X| = 0.
where γ = |W| + |Z| = |W| + |Z|. The above equation allows one to estimate p, i.e the length of the embedded message, based on X, Y , W, Z which can all be measured from the image being examined for possible steganography. Of course it should be noted that we cannot have γ = 0, the probability of which for natural images is very small.

Sources :

_[S. Dumitrescu, X. Wu, and N. Memon, “On steganalysis of random lsb embedding in continuous-tone images,” IEEE International Conference on Image Processing, ROchester, New York., September 2002.](http://simple-steganalysis-suite.googlecode.com/svn/wiki/articles/On_Steganalysis_of_Random_LSB_Embedding_in_Continuous-tone_Images.pdf)_


_[Rajarathnam Chandramouli, Mehdi Kharrazi, Nasir D. Memon: Image Steganography and Steganalysis: Concepts and Practice. IWDW 2003, Seoul, Korea](http://simple-steganalysis-suite.googlecode.com/svn/wiki/articles/Image_Steganography_and_Steganalysis_Concepts_and_Practice.pdf)_