package com.testvagrant.ekam.atoms;

import lombok.*;
import org.openqa.selenium.By;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiPlatformFinder {
  private By iosFindBy;
  private By androidFindBy;
  private By webFindBy;
  private By responsiveFindBy;
}
