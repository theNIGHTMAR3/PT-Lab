   M S B A M L   `   `   `  ����B  >Lab2_CS, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null!clr-namespace:Lab2_CSLab2_CS  5   	       Lab2_CS.CreationDialog   5   	   W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35^ ZWindowsFormsIntegration, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35H 9http://schemas.microsoft.com/winfx/2006/xaml/presentation      5   	   R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   'localclr-namespace:Lab2_CS     5   	     � Title$  CreationDialog��5   	   $	��317��6    $	��323��6-   .��5	      � $	��301��6   $��Bottom=�6   .+�5
      �� -   5         ��Name$ fileName��5
   '   $
��Left=�6   $��	80,24,0,0q�69   $
��Name��6L    �� TextWrapping$
 Wrap=�6X   $	��Top=�6l   $	��120��6�   $��20��6�   �� 5      $��Name:��6
   $
��Left=�6   $��	20,20,0,0q�65   $	��Top=�6H   	� 5      -   5         	�Name$ isFile��5      $
��File��6   $
��Left=�6/   $��	61,67,0,0q�6J   $	��Top=�6]   	� 5      -   5        $ isDir��5   $   $��	Directory��6    d� 	IsChecked$
 TrueY�63   $
��Left=�6D   $��
121,67,0,0q�6_   $	��Top=�6s   6�   �� 5      -   5         ��Name$ 
isReadOnly��5       $��ReadOnly��6   $
��Left=�64   $��
61,106,0,0q�6O   $	��Top=�6c   �� 5      -   5        $ 	isArchive��5      $��Archive��6   $
��Left=�62   $��
61,131,0,0q�6M   $	��Top=�6a    J� RenderTransformOrigin$ 0.577,-0.898&�6y   �� 5      -   5        $ isHidden��5      $��Hidden��6   $
��Left=�60   $��
61,155,0,0q�6K   $	��Top=�6_   �� 5      -   5        $ isSystem��5      $��System��6   $
��Left=�60   $��
61,180,0,0q�6K   $	��Top=�6_   �� 5      -   5         ��Name$ OKButtom��5      $��Ok��6   $
��Left=�6+   $��
80,264,0,0q�6F   $	��Top=�6Z   $��60��6r   6}   �� 5      -	   5        $ CancelButtom��5      $��Cancel��6   $
��Left=�62   $��180,264,0,0q�6M   $	��Top=�6b   $��60��6�   5      5      