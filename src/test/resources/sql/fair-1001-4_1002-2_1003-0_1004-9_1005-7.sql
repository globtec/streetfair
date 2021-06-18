INSERT INTO `fair`
    (
        `id`,
        `lat`,
        `lon`,
        `setcems`,
        `weighting_area`,
        `district_code`,
        `district`,
        `subprefecture_code`,
        `subprefecture`,
        `region5`,
        `region8`,
        `name`,
        `registry`,
        `address`,
        `number`,
        `neighborhood`,
        `landmark`
    )
SELECT * FROM
    (
        SELECT
            '506' AS `id`,
            '-46629044' AS `lat`,
            '-23556145' AS `lon`,
            '355030849000096' AS `setcems`,
            '3550308005008' AS `weighting_area`,
            '49' AS `district_code`,
            'LIBERDADE' AS `district`,
            '9' AS `subprefecture_code`,
            'SE' AS `subprefecture`,
            'Centro' AS `region5`,
            'Centro' AS `region8`,
            'THOMAZ DE LIMA' AS `name`,
            '1001-4' AS `registry`,
            'RUA TEIXEIRA LEITE C/ SAO PAULO' AS `address`,
            'S/N' AS `number`,
            'LIBERDADE' AS `neighborhood`,
            'BAIXOS VIADUTO GLICERIO AS `landmark'

        UNION ALL

        SELECT
            '216' AS `id`,
            '-46615785' AS `lat`,
            '-23538897' AS `lon`,
            '355030810000007' AS `setcems`,
            '3550308005005' AS `weighting_area`,
            '10' AS `district_code`,
            'BRAS' AS `district`,
            '25' AS `subprefecture_code`,
            'MOOCA' AS `subprefecture`,
            'Leste' AS `region5`,
            'Leste 1' AS `region8`,
            'CONCORDIA' AS `name`,
            '1002-2' AS `registry`,
            'RUA CONSELHEIRO BELISARIO' AS `address`,
            'S/N' AS `number`,
            'BRAS' AS `neighborhood`,
            'RUA MARIA MARCOLINA' AS `landmark`

        UNION ALL

        SELECT
            '36' AS `id`,
            '-46622239' AS `lat`,
            '-23578849' AS `lon`,
            '355030890000111' AS `setcems`,
            '3550308005046' AS `weighting_area`,
            '92' AS `district_code`,
            'VILA MARIANA' AS `district`,
            '12' AS `subprefecture_code`,
            'VILA MARIANA' AS `subprefecture`,
            'Sul' AS `region5`,
            'Sul 1' AS `region8`,
            'VILA DEODORO' AS `name`,
            '1003-0' AS `registry`,
            'RUA INGLES DE SOUZA' AS `address`,
            '618.000000' AS `number`,
            'CAMBUCI' AS `neighborhood`,
            'AV LINS DE VASCONCELOS' AS `landmark`

        UNION ALL

        SELECT
            '479' AS `id`,
            '-46700059' AS `lat`,
            '-23526596' AS `lon`,
            '355030848000012' AS `setcems`,
            '3550308005058' AS `weighting_area`,
            '48' AS `district_code`,
            'LAPA' AS `district`,
            '8' AS `subprefecture_code`,
            'LAPA' AS `subprefecture`,
            'Oeste' AS `region5`,
            'Oeste' AS `region8`,
            'LAPA' AS `name`,
            '1004-9' AS `registry`,
            'RUA FABIA C AURELIA' AS `address`,
            '701.000000' AS `number`,
            'VL ROMANA' AS `neighborhood`,
            'RUA CATAO' AS `landmark`

        UNION ALL

        SELECT
            '149' AS `id`,
            '-46600586' AS `lat`,
            '-23552696' AS `lon`,
            '355030853000042' AS `setcems`,
            '3550308005018' AS `weighting_area`,
            '54' AS `district_code`,
            'MOOCA' AS `district`,
            '25' AS `subprefecture_code`,
            'MOOCA' AS `subprefecture`,
            'Leste' AS `region5`,
            'Leste 1' AS `region8`,
            'MOOCA MODERNA' AS `name`,
            '1005-7' AS `registry`,
            'RUA DOS TRILHOS C BRESSER' AS `address`,
            'S/N' AS `number`,
            'BRESSER' AS `neighborhood`,
            'RUA BRESSER' AS `landmark`

    ) AS t
WHERE NOT EXISTS
    (
        SELECT 1
          FROM `fair`
         WHERE registry IN('1001-4', '1002-2', '1003-0', '1004-9', '1005-7')
    );