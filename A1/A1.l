%{


#include <stdio.h>
#include <string.h>
#include"A1.tab.h"



void yyerror(char*); 
int yyparse(void); 

%}

digit       [0-9] 
number     0|[1-9][0-9]*
space 		"\t"|"\n"|"\r"|"\f"|" " 

%%

"#define"                    {  return HASHDEFINE ; } 
"main"                       {   return MAIN ; }
"class"                      {  return CLASS ; }
"return"                     { return RETURN ;}
"void"                       { return  VOID ;}
"int"                        { return  INT ;  }
"float"                      { return  FLOAT ;  }
"public"                     { return  PUBLIC;}
"if"                         { return  IF;   }
"else"                       { return  ELSE ;   }
"while"                      { return  WHILE ;  }
"System.out.println"         { return  PRINT ;   }
"boolean"                    { return  BOOLEAN ;  }
"length"                     { return  LENGTH ;   }
"static"                     { return  STATIC ; }
"String"                     { return  STRING;    }
"extends"                    { return  EXTENDS ;    }
"new"                        { return  NEW ;    }
"this"                       { return  THIS  ;    }
"true"                       { return  TRRUE  ;    }
"false"                      { return  FAALSE ;   }



0|[1-9][0-9]*                    {
									yylval.text = (char*) malloc(strlen(yytext)*sizeof(char));
									yylval.text[0] = '\0';
									strcat(yylval.text,yytext);
									 return  NUM   ;   
						    }
[a-zA-Z_][a-zA-Z0-9_]*        {  	
									yylval.text = (char*) malloc(strlen(yytext)* sizeof(char) );
									yylval.text[0] = '\0';
									strcat(yylval.text,yytext);
									 return  ID    ;   
							 }



"&&"                         { return  CAND ;} // conditional AND 
"&"                         { return  AMP; }
";"                         {  return  SEMICOLON ;}
","                         { return  COMMA ; }
"."                         { return  DOT ;}
"<="                        { return  LE; }
">="                        { return  GE; }
"=="                        { return  ISEQ; }
"!="                         { return  NE;    }
"="		                       { return  EQUAL;    }
">"		                       { return  GT;    }
"<"		                       { return  LT;    }
"!"		                     { return  NOT;    }
"||"							{ return  COR ;    } // conditional OR 


"+"		                       { return  PLUS;    }
"-"		                       { return  SUB;     }
"/"		                       { return  DIV;    }
"*"		                     { return  MUL;    }
"%"		                     { return  MOD;    }



"("                         { return  LB ;    } 
")"                         { return  RB ;    } 
"{"                          {  return  FL;    }// flower bracket
"}"                           { return  FR ;    } // right flower bracket 
"["                          { return  LSQ ;    } // left square bracket 
"]"                         { return  RSQ ;    } // right square bracket 


"//".*			                        {;}//Single line comment when comments are found it should do nothing 
[/][*][^*]*[*]+([^][^*]*[*]+)*[/]		{;}//Multi line comment referred from https://stackoverflow.com/questions/25395251/detecting-and-skipping-line-comments-with-flex
{space}                                  {;} // skip spaces as well 


%%

void yyerror(char* str) { printf("//Failed to parse input code\n"); }

int yywrap(void) { return  1; } // wrap function to indicate the end of file for parser 

/*
int main(void) {
	yyparse();	
}*/
   