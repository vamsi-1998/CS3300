%{
   #include<stdio.h>
   #include<string.h>
   //strcat taken from string.h header file 
%}

%union
{
   char *text; 
}


 // Tokens 
%token <text> HASHDEFINE MAIN CLASS RETURN VOID INT FLOAT PUBLIC 
%token <text>   IF ELSE WHILE 
%token <text>  PRINT BOOLEAN LENGTH STATIC STRING EXTENDS NEW THIS TRRUE FAALSE 
%token <text>  NUM 
%token <text> ID 
%token <text> CAND AMP SEMICOLON COMMA DOT LE GE ISEQ NE EQUAL GT LT NEG 
%token <text> PLUS SUB DIV MUL MOD NOT
%token <text>  LB RB FL FR LSQ RSQ COR


// Non Terminals 
%type <text> Program  MainClass
%type <text> TypeDecstar TypeIdScstar MethodDeclarationstar TypeDeclaration MethodDeclaration
%type <text> Type ArgumentStar Statementstar Expression CommaTypeIDstar
%type <text> Statement  PrimaryExpression ExpressionIn ComExpStar Selstatement 
// %type <text> MacroDefinition MacroDefExpression MacroDefStatement 
// %type <text> IDinMacro CommaIdentifierStar

%nonassoc NOELSE
%nonassoc ELSE

%start Program 

%%

Program:           MainClass TypeDecstar {
                                                             $$ = (char*)malloc(50000*sizeof(char)); 
                                                             $$[0] = '\0';

                                                             strcat($$,$1); 
                                                             strcat($$,$2) ; 
                                                             printf("%s\n",$$);  
                                           };

TypeDecstar:   {              $$ = (char*)malloc(10*sizeof(char)); 
                             $$[0] = '\0';
               }
                |TypeDecstar TypeDeclaration 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                      $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$,$2) ; 
                                };

MainClass: CLASS ID FL PUBLIC STATIC VOID MAIN LB STRING LSQ RSQ ID RB FL PRINT LB Expression RB SEMICOLON FR FR
                {            
                      // main class 
                    $$ = (char*)malloc(10000*sizeof(char)); 
                    $$[0] = '\0';

                     strcat($$,"class ") ; 
                     strcat($$,$2) ;    
                     strcat($$,"{\n") ;      
                     strcat($$,"public ") ; 
                     strcat($$,"static ") ;
                     strcat($$,"void ") ;
                     strcat($$,"main") ;
                     strcat($$,"(") ;
                     strcat($$,"String[] ") ;
                     strcat($$,$12) ; 
                     strcat($$,")") ; 
                     strcat($$, "{\n") ; 
                     strcat($$, "\tSystem.out.println(") ; 
                     strcat($$,$17) ; 
                     strcat($$,");") ; 
                     strcat($$,"\n   }\n") ; 
                     strcat($$,"}\n") ;
                    // printf("\n\n\n %s\n\n\n",$$) ; 
                    
                };

TypeDeclaration: CLASS ID FL  TypeIdScstar MethodDeclarationstar FR 
                    {
                             $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';

                         strcat($$,"class ") ; 
                         strcat($$, $2) ; 
                         strcat ($$ , "{\n");
                         strcat ($$ , $4);
                         strcat ($$ , $5) ; 
                         strcat ($$ , "}\n"); 
                        //  printf("\n\n\n %s\n\n\n",$2) ;
                        //  printf("\n\n\n %s\n\n\n",$4) ;
                        //  printf("\n\n\n %s\n\n\n",$5) ;
                        //  printf("\n\n\n %s\n\n\n",$$) ;
                    }
                    |CLASS ID EXTENDS ID FL TypeIdScstar MethodDeclarationstar FR
                    {
                          $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';

                         strcat($$,"class ");
                         strcat($$,$2);
                         strcat($$," extends ");
                         strcat($$,$4);
                         strcat($$,"{\n" );
                         strcat($$,$6);
                         strcat($$,$7);
                         strcat($$,"}\n");

                    };

TypeIdScstar:   {
                        $$ = (char*)malloc(10*sizeof(char)); 
                             $$[0] = '\0';
                }
                |TypeIdScstar Type ID SEMICOLON 
                {
                             $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';

                     strcat($$,$1); 
                     strcat($$,$2) ; 
                     strcat($$,$3);
                     strcat($$,";\n") ; 
                };

MethodDeclarationstar:      {
                        $$ = (char*)malloc(10*sizeof(char)); 
                             $$[0] = '\0';
                              }
                            |MethodDeclarationstar MethodDeclaration 
                            {
                                  $$ = (char*)malloc(10000*sizeof(char)); 
                                 $$[0] = '\0';
                                 strcat($$,$1) ; 
                                 strcat($$,$2) ; 
                            };            

MethodDeclaration: PUBLIC Type ID LB ArgumentStar RB FL TypeIdScstar Statementstar RETURN Expression SEMICOLON FR
                        {   
                             $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';

                             strcat($$,"\tpublic ") ; 
                             strcat($$,$2) ; 
                             strcat($$," ") ;
                             strcat($$,$3) ; 
                             strcat($$,"(") ; 
                             strcat($$,$5) ; 
                             strcat($$,")") ; 
                             strcat($$,"{\n") ; 
                             strcat($$,$8) ; 
                             strcat($$,$9) ; 
                             strcat($$,"\treturn") ; 
                             strcat($$," ") ;
                             strcat($$,$11) ; 
                             strcat($$,";\n") ; 
                             strcat($$,"\t}\n") ;
                             //printf("%s\n",$$);
                        };
                       
Type:        INT LSQ RSQ {
                             $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';
                              strcat($$,"\tint[] ");
                        }
             |BOOLEAN { 
                              $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';
                             strcat($$,"\tboolean "); 
                        }
            |INT      {   
                             $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0'; 
                             strcat($$,"\tint ") ; 
                        }
            |ID       {  
                             $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0'; 
                             strcat($$,$1);
                             strcat($$," ");
                      };


ArgumentStar:  {
    $$ = (char*)malloc(10*sizeof(char)); 
                             $$[0] = '\0';
}
                |Type ID  CommaTypeIDstar
                    {
                          $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';

                         strcat($$,$1) ; 
                         
                         strcat($$,$2) ; 
                         strcat($$,$3) ; 
                    };


CommaTypeIDstar:    {
                        $$ = (char*)malloc(10*sizeof(char)); 
                        $$[0] = '\0';
                   }
                    |CommaTypeIDstar COMMA Type ID  
                        {
                              $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';

                             strcat($$,$1) ; 
                             strcat($$,",") ; 
                             strcat($$,$3) ; 
                             
                             strcat($$,$4) ; 
                        };


Statementstar:               {
                              $$ = (char*)malloc(10*sizeof(char)); 
                             $$[0] = '\0';
                              }
                |Statement Statementstar 
                        {
                              $$ = (char*)malloc(10000*sizeof(char)); 
                             $$[0] = '\0';
                             strcat($$ , $1) ; 
                             strcat($$ , $2) ; 
                        }; 



Selstatement: IF LB Expression RB Statement   %prec NOELSE
                            {
                                  $$ = (char*)malloc(10000*sizeof(char)); 
                                  $$[0] = '\0';

                                 strcat($$,"\t") ;
                                 strcat($$,"if(") ; 
                                 strcat($$,$3) ; 
                                 strcat($$,")") ; 
                                 strcat($$,$5) ; 
                            }
            |IF LB Expression RB Statement ELSE Statement 
                        {
                              $$ = (char*)malloc(10000*sizeof(char)); 
                              $$[0] = '\0';

                             strcat($$,"\t") ;
                             strcat($$,"if(") ; 
                             strcat($$,$3) ; 
                             strcat($$,")") ; 
                             strcat($$,$5) ; 
                             strcat($$,"else ") ; 
                             strcat($$,$7) ; 
                        };

Statement: FL Statementstar FR
                    {
                          $$ = (char*)malloc(10000*sizeof(char)); 
                          $$[0] = '\0';
                         
                         
                         strcat($$,"\t\n{") ; 
                         strcat($$,$2) ; 
                         strcat($$,"\t}\n") ; 
                    } 
            |PRINT LB Expression RB SEMICOLON 
                    {
                          $$ = (char*)malloc(10000*sizeof(char)); 
                          $$[0] = '\0';
                         
                         strcat($$,"\t") ;
                         strcat($$,"System.out.println(") ; 
                         strcat($$,$3) ; 
                         strcat($$,");\n") ; 
                    } 
            |ID EQUAL Expression SEMICOLON  
                            {
                                  $$ = (char*)malloc(10000*sizeof(char)); 
                                  $$[0] = '\0';
                                 
                                 strcat($$,"\t") ;
                                 strcat($$,$1) ; 
                                 strcat($$,"=") ; 
                                 strcat($$,$3) ; 
                                 strcat($$,";\n") ; 
                            } 

            |ID LSQ Expression RSQ EQUAL Expression SEMICOLON 
                            {
                                  $$ = (char*)malloc(10000*sizeof(char)); 
                                  $$[0] = '\0';

                                     strcat($$,"\t") ;
                                     strcat($$,$1) ;
                                     strcat($$,"[") ; 
                                     strcat($$,$3) ; 
                                     strcat($$,"]") ; 
                                     strcat($$,"=") ; 
                                     strcat($$,$6) ; 
                                     strcat($$,";\n") ; 

                            } 

            |Selstatement 
                            {
                                  $$ = (char*)malloc(10000*sizeof(char)); 
                                  $$[0] = '\0';

                                     strcat($$,"\t") ;
                                     strcat($$,$1) ;
                            }

            |WHILE LB Expression RB Statement 
                            {
                                  $$ = (char*)malloc(10000*sizeof(char)); 
                                  $$[0] = '\0';

                                 strcat($$,"\t") ;
                                 strcat($$,"while(") ; 
                                 strcat($$,$3) ; 
                                 strcat($$,")") ; 
                                 strcat($$,$5) ; 
                            }; 
            

ComExpStar:                 {
                                    $$ = (char*)malloc(10*sizeof(char)); 
                                    $$[0] = '\0';
                            }
                            |ComExpStar COMMA Expression
                            {
                                     $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                     strcat($$,$1);
                                     strcat($$,",");
                                     strcat($$,$3);
                            };


Expression: PrimaryExpression CAND PrimaryExpression 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                      $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$," && ") ; 
                                     strcat($$,$3) ; 
                                } 
            |PrimaryExpression COR PrimaryExpression 
                            {
                                  $$ = (char*)malloc(10000*sizeof(char)); 
                                  $$[0] = '\0';

                                 strcat($$,$1) ; 
                                 strcat($$," || ") ; 
                                 strcat($$,$3) ; 
                            } 
            |PrimaryExpression NE PrimaryExpression 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$," != ") ; 
                                     strcat($$,$3) ; 
                                }
            |PrimaryExpression LE PrimaryExpression 
                        {
                              $$ = (char*)malloc(10000*sizeof(char)); 
                              $$[0] = '\0';

                                 strcat($$,$1) ; 
                                 strcat($$," <= ") ; 
                                 strcat($$,$3) ; 
                        }
            |PrimaryExpression PLUS PrimaryExpression 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                      $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$," + ") ; 
                                     strcat($$,$3) ; 
                                }
            |PrimaryExpression SUB PrimaryExpression
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$," - ") ; 
                                     strcat($$,$3) ; 
                                }
            |PrimaryExpression MUL PrimaryExpression
                                 {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$,"*") ; 
                                     strcat($$,$3) ; 
                                }
            |PrimaryExpression DIV PrimaryExpression
                                 {
                                       $$ = (char*)malloc(10000*sizeof(char)); 
                                         $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$,"/") ; 
                                     strcat($$,$3) ; 
                                }
            |PrimaryExpression LSQ PrimaryExpression RSQ
                                 {
                                       $$ = (char*)malloc(10000*sizeof(char)); 
                                       $$[0] = '\0';

                                         strcat($$,$1) ; 
                                         strcat($$,"[") ; 
                                         strcat($$,$3) ; 
                                         strcat($$,"]") ;
                                }

            |PrimaryExpression DOT LENGTH 
                                 {
                                       $$ = (char*)malloc(10000*sizeof(char)); 
                                       $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$,".length") ; 
                                }
            |PrimaryExpression 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                      $$[0] = '\0';

                                     strcat($$,$1) ;  
                                }
            |PrimaryExpression DOT ID LB ExpressionIn RB
                                 {
                                       $$ = (char*)malloc(10000*sizeof(char)); 
                                      $$[0] = '\0';

                                     strcat($$,$1) ; 
                                     strcat($$,".") ; 
                                     strcat($$,$3) ; 
                                     strcat($$,"(") ;
                                     strcat($$,$5) ;
                                     strcat($$,")") ;
                                     //printf("\n hello : %s ",$5) ; 
                                 };

ExpressionIn:           {
                              $$ = (char*)malloc(10*sizeof(char)); 
                             $$[0] = '\0';
                         }
                        |Expression ComExpStar
                        {
                                     $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                     strcat($$,$1);
                                     //printf("\n hello : %s ",$1) ; 
                                     strcat($$,$2);
                                    // printf("\n hello : %s ",$2) ; 

                        };  

PrimaryExpression: NUM 
                        {
                                 $$ = (char*)malloc(10000*sizeof(char)); 
                                 $$[0] = '\0';
                                
                                 strcat($$,$1) ;
                        } 
                   |TRRUE 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                      strcat($$,"true") ;
                                } 
                   |FAALSE 
                                    {
                                         $$ = (char*)malloc(10000*sizeof(char)); 
                                         $$[0] = '\0';

                                        strcat($$,"false") ;
                                    } 
                   |ID 
                                    {
                                          $$ = (char*)malloc(10000*sizeof(char)); 
                                          $$[0] = '\0';

                                          strcat($$,$1) ;
                                    } 
                   |THIS 
                                    {
                                          $$ = (char*)malloc(10000*sizeof(char)); 
                                         $$[0] = '\0';

                                        strcat($$,"this") ;
                                    } 
                   |NEW INT LSQ Expression RSQ 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                     strcat($$,"new int ") ;
                                     strcat($$," [ ") ;
                                     strcat($$,$4) ;
                                     strcat($$,"]\n") ;

                                } 
                   |NEW ID LB RB 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                     strcat($$,"new ") ;
                                     strcat($$,$2) ;
                                     strcat($$,"()") ;
                                    
                                } 
                   |NOT Expression 
                                    {
                                          $$ = (char*)malloc(10000*sizeof(char)); 
                                         $$[0] = '\0';

                                         strcat($$,"!") ;
                                         strcat($$,$2) ;
                                    } 
                   |LB Expression RB 
                                {
                                      $$ = (char*)malloc(10000*sizeof(char)); 
                                     $$[0] = '\0';

                                     strcat($$,"(") ;
                                     strcat($$,$2) ;
                                     strcat($$,")") ;
                                }; 

%%

    int main(int argc, char **argv)
    {
    yyparse();
    }
    