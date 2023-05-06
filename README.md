# Editordecolores
Pequeño proyecto en Java que aplica filtros a imágenes centrado en modificar sus colores.


Parte del código proviene de:

https://stackoverflow.com/questions/15322329/how-to-display-an-image-represented-by-r-g-b-values-separately-in-3-matrices-in
(Respuesta de Alya'a Gamal)


https://stackoverflow.com/questions/4801366/convert-rgb-values-to-integer
(Respuesta de camickr)

https://www.tutorialspoint.com/how-to-get-pixels-rgb-values-of-an-image-using-java-opencv-library#:~:text=Retrieving%20the%20pixel%20contents%20(ARGB%20values)%20of%20an%20image%20%E2%88%92&text=Get%20the%20pixel%20value%20at,and%20getBlue()%20methods%20respectively.
(La parte en la que se describe como leer valores RGB de un archivo)





**- RGBint**
Esta clase modela un pixel
Es muy sencilla y esta hecha para cambiar entre dos formatos para expresar los valores RGB que se usan, así como ser modificados de distintas maneras

**- RGBlector**
Aquí esta la mayoría del código. Por una parte, se usa para leer imágenes del ordenador y transformarlas en un array de RGBint (pixels). Por otra se usa para modificar la imagen de distintas maneras. Los efectos en general no son muy vistosos. Son filtros simples que sirven para suavizar los cambios de color, filtrar colores, o potenciarlos de distintas maneras.


**- Pruebas6**
Aquí hay un ejemplo de código que cambiando simplemente el nombre y formato de la imagen (suponiendo que sea válida para el programa) le aplique diversas transformaciones. Estas se pueden modificar también con facilidad.


Foto original:
![Im](/foto1.jpg)

Tras ser transformada:

![Im](/foto1-filtro-2D.jpg)
![Im](/foto1-filtro-2D-gradientes.jpg)
![Im](/foto1-filtro-horizontal.jpg)
![Im](/foto1-filtro-vertical.jpg)
![Im](/foto1-sin-rojos.jpg)



