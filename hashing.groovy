import java.security.MessageDigest
def resultFile = new File('result.txt')
def res=resultFile.getText('UTF-8')
println MessageDigest.getInstance("MD5").digest(res.bytes).encodeHex().toString()