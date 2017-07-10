<?php
/**
 * 练习php的
 * file读写
 * json_decode
 * iconv
 * preg_match_all
 * base_convert
 * strpos
 *
 * 代码做了修改，字段对应不上
 *
 * User: dc0453
 * Date: 2017/6/29
 * Time: 下午4:00
 */

$file = fopen('infile', 'r');
$o = fopen('outfile', 'w');
fwrite($o,iconv("UTF-8", "GBK","订单ID|订单号|快递公司|快递单号|下单日期|商品id|进货价格|姓名|电话|邮编|地区|详细地址|消耗积分|发货日期\n"));
while (!feof($file)) {
    $line = fgets($file);
    $arr = explode('|', $line);
    if (is_array($arr) && count($arr)) {
        $resultInfoStr = trim($arr[6]);
        $remarkInfoStr = trim($arr[7]);

        $resultInfo = json_decode($resultInfoStr, true);
        $remarkInfo = json_decode($remarkInfoStr, true);

        $outLine = $arr[0]."|".$arr[1]."|".$arr[2]."|".$resultInfo["express_company"]."|".$resultInfo["express_number"]."|".$arr[3]."|".
            $arr[4]."|".$arr[5]."|".unicode_decode($remarkInfo["username"])."|".unicode_decode($remarkInfo["phone"])."|".$remarkInfo["postcode"]."|".unicode_decode($remarkInfo["region"])."|".
            unicode_decode($remarkInfo["detail"])."|".$arr[8];
        $outLine = iconv("UTF-8", "GBK", $outLine);
        fwrite($o, $outLine);
    }
}
//fwrite($o,iconv("UTF-8", "GBK","订单ID|订单号|下单日期|商品名称|面值|充值号码|消耗积分|到账日期\n"));
//while (!feof($file)) {
//    $line = fgets($file);
//    $arr = explode('|', $line);
//    if (is_array($arr) && count($arr)) {
//        $resultInfoStr = trim($arr[6]);
//        $remarkInfoStr = trim($arr[7]);
//
//        $resultInfo = json_decode($resultInfoStr, true);
//        $remarkInfo = json_decode($remarkInfoStr, true);
//
//        $outLine = $arr[0]."|".$arr[1]."|".$arr[2]."|".$arr[3]."|".$arr[4]."|".$arr[5]."|".$remarkInfo["recharge_account"]."|".$arr[5]."|".$arr[8];
//        $outLine = iconv("UTF-8", "GBK", $outLine);
//        fwrite($o, $outLine);
//    }
//}

fclose($file);
fclose($o);

function unicode_decode($name,$out_charset='UTF-8',$in_charset='UCS-2BE')
{
    // 转换编码，将Unicode编码转换成可以浏览的utf-8编码
    $pattern = '/([\x{4e00}-\x{9fa5}A-Za-z0-9]+)|(\\\u([\w]{4}))/u';
    preg_match_all($pattern, $name, $matches);
//    print_r($matches);
    if (!empty($matches)){
        $name = '';
        for ($j = 0; $j < count($matches[0]); $j++){
            $str = $matches[0][$j];
            if (strpos($str, '\\u') === 0){
                $code = base_convert(substr($str, 2, 2), 16, 10);
                $code2 = base_convert(substr($str, 4), 16, 10);
                $c = chr($code).chr($code2);
                $c = iconv($in_charset, $out_charset, $c);
                $name .= $c;
            }
            else{
                $name .= $str;
            }
        }
    }
    return $name;
}
