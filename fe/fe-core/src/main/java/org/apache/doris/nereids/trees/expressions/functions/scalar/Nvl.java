// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.nereids.trees.expressions.functions.scalar;

import org.apache.doris.catalog.FunctionSignature;
import org.apache.doris.nereids.trees.expressions.Expression;
import org.apache.doris.nereids.trees.expressions.functions.ExplicitlyCastableSignature;
import org.apache.doris.nereids.trees.expressions.shape.BinaryExpression;
import org.apache.doris.nereids.trees.expressions.visitor.ExpressionVisitor;
import org.apache.doris.nereids.types.BigIntType;
import org.apache.doris.nereids.types.BitmapType;
import org.apache.doris.nereids.types.BooleanType;
import org.apache.doris.nereids.types.DateTimeType;
import org.apache.doris.nereids.types.DateTimeV2Type;
import org.apache.doris.nereids.types.DateType;
import org.apache.doris.nereids.types.DateV2Type;
import org.apache.doris.nereids.types.DecimalV2Type;
import org.apache.doris.nereids.types.DecimalV3Type;
import org.apache.doris.nereids.types.DoubleType;
import org.apache.doris.nereids.types.FloatType;
import org.apache.doris.nereids.types.IntegerType;
import org.apache.doris.nereids.types.LargeIntType;
import org.apache.doris.nereids.types.SmallIntType;
import org.apache.doris.nereids.types.StringType;
import org.apache.doris.nereids.types.TinyIntType;
import org.apache.doris.nereids.types.VarcharType;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * ScalarFunction 'nvl'. This class is generated by GenerateFunction.
 */
public class Nvl extends ScalarFunction
        implements BinaryExpression, ExplicitlyCastableSignature {

    public static final List<FunctionSignature> SIGNATURES = ImmutableList.of(
            FunctionSignature.ret(BooleanType.INSTANCE).args(BooleanType.INSTANCE, BooleanType.INSTANCE),
            FunctionSignature.ret(TinyIntType.INSTANCE).args(TinyIntType.INSTANCE, TinyIntType.INSTANCE),
            FunctionSignature.ret(SmallIntType.INSTANCE).args(SmallIntType.INSTANCE, SmallIntType.INSTANCE),
            FunctionSignature.ret(IntegerType.INSTANCE).args(IntegerType.INSTANCE, IntegerType.INSTANCE),
            FunctionSignature.ret(BigIntType.INSTANCE).args(BigIntType.INSTANCE, BigIntType.INSTANCE),
            FunctionSignature.ret(LargeIntType.INSTANCE).args(LargeIntType.INSTANCE, LargeIntType.INSTANCE),
            FunctionSignature.ret(DoubleType.INSTANCE).args(DoubleType.INSTANCE, DoubleType.INSTANCE),
            FunctionSignature.ret(FloatType.INSTANCE).args(FloatType.INSTANCE, FloatType.INSTANCE),
            FunctionSignature.ret(DateType.INSTANCE).args(DateType.INSTANCE, DateType.INSTANCE),
            FunctionSignature.ret(DateTimeType.INSTANCE).args(DateTimeType.INSTANCE, DateTimeType.INSTANCE),
            FunctionSignature.ret(DateTimeType.INSTANCE).args(DateType.INSTANCE, DateTimeType.INSTANCE),
            FunctionSignature.ret(DateTimeType.INSTANCE).args(DateTimeType.INSTANCE, DateType.INSTANCE),
            FunctionSignature.ret(DateTimeV2Type.SYSTEM_DEFAULT)
                    .args(DateTimeV2Type.SYSTEM_DEFAULT, DateTimeV2Type.SYSTEM_DEFAULT),
            FunctionSignature.ret(DateV2Type.INSTANCE)
                    .args(DateV2Type.INSTANCE, DateV2Type.INSTANCE),
            FunctionSignature.ret(DecimalV3Type.WILDCARD)
                    .args(DecimalV3Type.WILDCARD, DecimalV3Type.WILDCARD),
            FunctionSignature.ret(DecimalV2Type.SYSTEM_DEFAULT)
                    .args(DecimalV2Type.SYSTEM_DEFAULT, DecimalV2Type.SYSTEM_DEFAULT),
            FunctionSignature.ret(BitmapType.INSTANCE).args(BitmapType.INSTANCE, BitmapType.INSTANCE),
            FunctionSignature.ret(VarcharType.SYSTEM_DEFAULT)
                    .args(VarcharType.SYSTEM_DEFAULT, VarcharType.SYSTEM_DEFAULT),
            FunctionSignature.ret(StringType.INSTANCE).args(StringType.INSTANCE, StringType.INSTANCE)
    );

    /**
     * constructor with 2 arguments.
     */
    public Nvl(Expression arg0, Expression arg1) {
        super("nvl", arg0, arg1);
    }

    /**
     * custom compute nullable.
     */
    @Override
    public boolean nullable() {
        return child(0).nullable() && child(1).nullable();
    }

    /**
     * withChildren.
     */
    @Override
    public Nvl withChildren(List<Expression> children) {
        Preconditions.checkArgument(children.size() == 2);
        return new Nvl(children.get(0), children.get(1));
    }

    @Override
    public List<FunctionSignature> getSignatures() {
        return SIGNATURES;
    }

    @Override
    public <R, C> R accept(ExpressionVisitor<R, C> visitor, C context) {
        return visitor.visitNvl(this, context);
    }
}
